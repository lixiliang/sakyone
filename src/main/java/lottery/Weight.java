package lottery;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Slf4j
public class Weight {

    /**
     * 根据Math.random()产生一个double型的随机数，判断每个奖品出现的概率
     *
     * @param awardList
     * @return random：奖品列表prizes中的序列（prizes中的第random个就是抽中的奖品）
     */
    public static int luckyDraw(List<Award> awardList) {
//        Collections.shuffle(awardList);
        int random = -1;
        try {
            //计算总权重
            double sumWeight = 0;
            for (Award p : awardList) {
                sumWeight += p.getWeight();
            }

            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < awardList.size(); i++) {
                d2 += (double) (awardList.get(i).getWeight()) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += (double) (awardList.get(i - 1).getWeight()) / sumWeight;
                }
                if (randomNumber >= d1 && randomNumber <= d2) {
                    random = i;
                    break;
                }
            }
        } catch (Exception e) {
            random = -1;
            log.info("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        if(random == -1){
            log.error("error");
        }
        return random;
    }
    public static int luckyDraw2(List<Award> awardList) {
//        Collections.shuffle(awardList);
        int random = -1;
        try {
            //计算总权重
            int sumWeight = 0;
            for (Award p : awardList) {
                sumWeight += p.getWeight();
            }

            //产生随机数
            int randomNumber = (int)(Math.random() * (sumWeight +1));

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            int d1 = 0;
            int d2 = 0;
            for (int i = 0; i < awardList.size(); i++) {
                d2 += (awardList.get(i).getWeight());
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += (awardList.get(i - 1).getWeight()) ;
                }
                if (randomNumber >= d1 && randomNumber <= d2) {
                    random = i;
                    break;
                }
            }
        } catch (Exception e) {
            random = -1;
            log.info("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        if(random == -1){
            log.error("error");
        }
        return random;
    }

    public static void main(String[] args) {
        List<Award> awardList = Lists.newArrayList();
        awardList.add(new Award(188, 34415));
        awardList.add(new Award(166, 30215));
        awardList.add(new Award(99, 28120));
        awardList.add(new Award(256, 5430));
        awardList.add(new Award(999, 1502));
        awardList.add(new Award(2999, 257));
        awardList.add(new Award(13140, 61));

      /*  oneTest(awardList,1000);
        oneTest(awardList,10000);
        oneTest(awardList,50000);
        oneTest(awardList,100000);
        oneTest(awardList,500000);
        oneTest(awardList,1000000);
        oneTest(awardList,2000000);
        oneTest(awardList,5000000);*/
      long start = System.currentTimeMillis();
        oneTest(awardList,100000000);
        log.info("cost:{} ms ",System.currentTimeMillis() -start);
        start = System.currentTimeMillis();
        oneTest(awardList,100000000);
        log.info("cost:{} ms ",System.currentTimeMillis() -start);

    }

    public static void  oneTest(List<Award> awardList,int total){
        Map<Integer, Integer> result = Maps.newHashMap();
        for (int i = 0; i < total; i++) {
            Award award = awardList.get(luckyDraw2(awardList));
//            log.info("{} get award:{}",i,award);
            if (result.containsKey(award.getPrice())) {
                result.put(award.getPrice(), result.get(award.getPrice()) + 1);
            } else {
                result.put(award.getPrice(), 1);
            }
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(4);
        log.info("抽取总数：{}",total);
        result.entrySet().stream().sorted(Comparator.comparing((i)->i.getValue())).forEach(i -> log.info("{},rate:{}", i, numberFormat.format((float) i.getValue() / (float) total * 100)));
    }
}
