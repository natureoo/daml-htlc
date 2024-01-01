# daml-htlc

    Copyright (c) 2023, ASX Operations Pty Ltd. All rights reserved.
    SPDX-License-Identifier: Apache-2.0

Hashed timelock contract written in Daml.

# Building and testing
First, the dependencies must be downloaded by running:

```bash
./get-dependencies.sh
```

The daml package can then be built using:

```bash
cd model/main
daml build
```

And the tests can be run using:

```bash
cd model/test
daml test
```

daml sandbox --dar /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/interoperability/htlc/daml-htlc/model/main/.daml/dist/daml-htlc-model-0.2.0.dar


daml ledger upload-dar /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/daml/daml-finance/quickstart-finance/.lib/daml-finance-holding.dar  --host localhost --port 6865

<!-- daml ledger upload-dar /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/daml/daml-finance/quickstart-finance/.lib/daml-finance-interface-account.dar -->

daml ledger upload-dar /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/interoperability/htlc/daml-htlc/.lib/daml-finance-account.dar  --host localhost --port 6865

<!-- daml ledger upload-dar /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/daml/daml-finance/quickstart-finance/.lib/daml-finance-interface-account.dar

daml ledger upload-dar  /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/interoperability/htlc/daml-htlc/.lib/daml-finance-holding.dar
daml ledger upload-dar  /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/interoperability/htlc/daml-htlc/.lib/daml-finance-interface-types-common.dar
  # IMPLEMENTATION DEPENDENCIES
daml ledger upload-dar /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/interoperability/htlc/daml-htlc/.lib/daml-finance-account.dar
daml ledger upload-dar  /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/interoperability/htlc/daml-htlc/.lib/daml-finance-holding.dar
daml ledger upload-dar /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/interoperability/htlc/daml-htlc/.lib/daml-finance-interface-util.dar -->


daml ledger allocate-parties --host localhost --port 6865 Alice
# Alice::12204e44825e08464a2f707a1d0ba064621d6e2235037d3129c128f578619360efc7
daml ledger allocate-parties --host localhost --port 6865 Bob
# Bob::12204e44825e08464a2f707a1d0ba064621d6e2235037d3129c128f578619360efc7
daml ledger allocate-parties --host localhost --port 6865 Bank
# Bank::12204e44825e08464a2f707a1d0ba064621d6e2235037d3129c128f578619360efc7

daml ledger allocate-parties --host localhost --port 6865 Government
# Government::12204e44825e08464a2f707a1d0ba064621d6e2235037d3129c128f578619360efc7

Government

cd /Users/chenjian/Documents/chenj/work/workplace/study/blockchain/interoperability/htlc/daml-htlc/model/main
daml navigator server --config-file ./ui-backend.conf


Alice 是买方，有cash， Bob是卖方，有bond


1. 买方发起资产投资请求，买方在所在的资金分布式账本系统生成密钥并计算哈希值；
 
2. 买方在所在的资金分布式账本系统使用密钥锁定待交易资金并转移给卖方；
 
3. 买方向卖方提供密钥哈希值, 卖方在所在的数字资产分布式账本系统上锁定待交易数字资产并转移给买方；
 
4. 买方在数字资产分布式账本系统上解锁卖方锁定的待交易数字资产，完成在该账本内的资产转移，同时广播密钥；
 
5. 卖方在资金分布式账本上用密钥解锁买方锁定的待交易资金, 交易流程结束。
 
使用besu实现资金分布式账本系统 daml实现数字资产分布式账本系统