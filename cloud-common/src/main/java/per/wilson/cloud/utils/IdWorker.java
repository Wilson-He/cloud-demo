package per.wilson.cloud.utils;

import java.util.HashSet;
import java.util.Set;

public class IdWorker {

    /**
     * 起始时间戳
     */
    private final static long START_TIMESTAMP = 1288834974657L;

    /**
     * 每一部分(序号、机器标识、数据中西)占用的位数
     */
    private final static long SEQUENCE_BIT = 12;
    private final static long MACHINE_BIT = 5;
    private final static long DATA_CENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_SHIFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_ID_SHIFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT_SHIFT = DATA_CENTER_ID_SHIFT + DATA_CENTER_BIT;

    private long dataCenterId;
    private long machineId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public IdWorker(long dataCenterId, long machineId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException(
                    "machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        if (timestamp == lastTimestamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                timestamp = tilNextMillis();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        //时间戳部分
        return (timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT
                //数据中心部分
                | dataCenterId << DATA_CENTER_ID_SHIFT
                //机器标识部分
                | machineId << MACHINE_SHIFT
                //序列号部分
                | sequence;
    }

    private long tilNextMillis() {
        long mill = timeGen();
        while (mill <= lastTimestamp) {
            mill = timeGen();
        }
        return mill;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(0, 0);
        Set<Long> set = new HashSet<>(10000);
        for (int i = 0; i < 50000; i++) {
            new Thread(() -> set.add(idWorker.nextId())).run();
        }
        System.out.println(set.size());
    }
}