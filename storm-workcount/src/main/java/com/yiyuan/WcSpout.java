package com.yiyuan;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Values;

import java.util.List;
import java.util.Map;

public class WcSpout extends BaseRichSpout {


    private Map map;
    private SpoutOutputCollector collector;
    private TopologyContext topologyContext;
    int i = 0;

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.map = map;
        this.topologyContext = topologyContext;
        this.collector = spoutOutputCollector;
    }

    public void nextTuple() {
        i ++;
        List tuple = new Values((Integer)i);
        this.collector.emit(tuple);

        System.out.println("spout---------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
//        dec
    }
}
