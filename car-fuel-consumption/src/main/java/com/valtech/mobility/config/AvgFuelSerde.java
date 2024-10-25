package com.valtech.mobility.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;

public class AvgFuelSerde extends Serdes.WrapperSerde<double[]> {
public AvgFuelSerde() {

    super(new Serializer<double[]>() {
        @Override
        public byte[] serialize(String topic, double[] data) {
            ByteBuffer buffer = ByteBuffer.allocate(16);
            buffer.putDouble(data[0]);
            buffer.putInt((int) data[1]);
            return buffer.array();
        }
    }, new Deserializer<double[]>() {
        @Override
        public double[] deserialize(String topic, byte[] data) {
            ByteBuffer buffer = ByteBuffer.wrap(data);
            return new double[]{buffer.getDouble(), buffer.getInt()};
        }
    });
}
}
