# Curios Ability Addon

Minecraft Forge 1.20.1用の `abilitysgorwmod` 向けCuriosアドオンです。Curiosと本体MOD 1.0.5以降が必要です。

## 追加アイテムと装備方法

| アイテム | 効果 |
| --- | --- |
| 幸運増幅レンズ | 本体MODの確率発動能力の成功率を+30ポイント |
| 戦技の護符 | 会心・回避・自動反撃を+20ポイント、攻撃力上昇I |
| 豊穣の護符 | 作物成長・草原の幸運を+25ポイント、採掘速度上昇I・幸運I |
| 宝運の護符 | ドロップ複製・無償の魔術を+25ポイント、幸運II |
| 生命の護符 | 再生能力I・耐性I |
| 俊敏の護符 | 移動速度上昇II・跳躍力上昇I |

プレイヤー用の「能力強化」Curiosスロットを3枠追加します。6種類のアイテムはこの専用スロットに装備できます。各アイテムのアイコンには提供された青と金のレンズ画像を使っています。アイテムはエンドシティと砂漠のピラミッドのチェストに40%の確率で、いずれか1個が追加で入ります。

## ビルド

1. `teramodGuard`で `gradlew build` を実行します。アドオンの `runClient` は、この本体MOD JARを開発実行時に読み込みます。
2. このフォルダで `gradlew build` を実行します。
3. 生成された `build/libs/abilitysgorwmod-1.0.5.jar` と `build/libs/curiosAbilityAddon-1.0.0.jar`、Curios本体のForge 1.20.1版を、Minecraftの同じ `mods` フォルダに入れます。

アドオンは本体MODの `AbilityApi.getAbilityChance` / `AbilityChanceEvent` を使って、確率能力が抽選される直前に成功率を加算します。