package com.kdata.track;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerHelper implements Runnable{

	private KafkaProducer<String, String> kafkaProducer = null;
	private ProducerRecord<String, String> record;

	public KafkaProducerHelper(String kafkaHosts,String topic, String key, String values) {
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHosts);
		props.setProperty(ProducerConfig.ACKS_CONFIG, "1");
		props.setProperty(ProducerConfig.RETRIES_CONFIG, "1");
		props.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "120000");
		props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
		props.setProperty(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "8388608");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		kafkaProducer = new KafkaProducer<String, String>(props);
		record = new ProducerRecord<String, String>(topic, key, values);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		kafkaProducer.send(record);
		kafkaProducer.close();
	}

}
