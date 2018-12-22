package com.yiyuan.test;

import com.yiyuan.bean.Item;
import com.yiyuan.bean.Order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	@org.junit.Test
	public void test(){
		List<Order> orderList = Stream.generate(() -> new Order("yukang",Stream.generate(() -> new Item("1","hello")).limit(10).collect(Collectors.toList()))).limit(10).collect(Collectors.toList());
		Stream<Item> itemStream = orderList.stream().flatMap(order -> order.getItemList().stream());

		System.out.println(itemStream.count());


	}
}
