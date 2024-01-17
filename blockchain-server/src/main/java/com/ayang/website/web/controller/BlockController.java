package com.ayang.website.web.controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.ayang.website.web.service.BlockService;
import com.ayang.website.web.service.PowService;
import com.ayang.website.web.util.BlockCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BlockController {

	@Resource
	BlockService blockService;
	
	@Resource
	PowService powService;
	
	@Autowired
	BlockCache blockCache;
	
	/**
	 * 查看当前节点区块链数据
	 * @return
	 */
	@GetMapping("/scan")
	@ResponseBody
	public String scanBlock() {
		return JSON.toJSONString(blockCache.getBlockChain());
	}
	
	/**
	 * 查看当前节点区块链数据
	 * @return
	 */
	@GetMapping("/data")
	@ResponseBody
	public String scanData() {
		return JSON.toJSONString(blockCache.getPackedTransactions());
	}
	
	/**
	 * 创建创世区块
	 * @return
	 */
	@GetMapping("/create")
	@ResponseBody
	public String createFirstBlock() {
		blockService.createGenesisBlock();
		return JSON.toJSONString(blockCache.getBlockChain());
	}
	
	/**
	 * 工作量证明PoW
	 * 挖矿生成新的区块 
	 */
	@GetMapping("/mine")
	@ResponseBody
	public String createNewBlock() {
		powService.mine();
		return JSON.toJSONString(blockCache.getBlockChain());
	}
}