#!/bin/bash
# Nexus 正式版发布脚本：构建前临时移除测试钩子代码，构建后恢复源码
set -e
cd "$(dirname "$0")"
SRC=src/main/java/net/nexus/NexusClient.java
BAK=/tmp/NexusClient.java.bak

cp "$SRC" "$BAK"
python3 - "$SRC" <<'PYEOF'
import sys
p = sys.argv[1]
s = open(p, encoding='utf-8').read()
BEG = '// [TEST-HOOK-BEGIN]'
END = '// [TEST-HOOK-END]'
n = 0
out = []
while True:
    i = s.find(BEG)
    if i < 0:
        out.append(s)
        break
    j = s.find(END, i)
    if j < 0:
        print('ERROR: unterminated TEST-HOOK block'); sys.exit(1)
    out.append(s[:i])
    s = s[j + len(END):]
    n += 1
open(p, 'w', encoding='utf-8').write(''.join(out))
print('removed test-hook blocks:', n)
PYEOF

export JAVA_HOME=/home/user/tools/jdk-21.0.12.1+1
export PATH=$JAVA_HOME/bin:$PATH
./gradlew clean build -q
cp build/libs/Nexus-Client-a0.8.jar "../Nexus a0.8 1.21.11.jar"
# 恢复源码（测试钩子保留在源码中供开发测试用）
cp "$BAK" "$SRC"
# 验证 jar 内无测试代码
unzip -p "../Nexus a0.8 1.21.11.jar" net/nexus/NexusClient.class > /tmp/NexusClient.class
javap -p /tmp/NexusClient.class | grep -ciE "testGui|runTestHook" | grep -q '^0$' && echo "VERIFY OK: no test code in jar"
echo "RELEASE BUILT"
