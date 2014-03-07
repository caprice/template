package com.gm.infobus.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyTool {

	public static void main(String[] args) {
		MyTool tool = new MyTool();
		Set<String> weSet = tool.generateDataSetFromTextFile("c:/we.txt");
		Set<String> heSet = tool.generateDataSetFromTextFile("c:/he.txt");
		List<String> list = new ArrayList<String>();
		if(weSet!=null&&heSet!=null){
			for(String key:heSet){
				if(!weSet.contains(key)){
					list.add(key);
				}
			}
		}
		for(String key:list){
			System.out.println(key);
		}

	}

	/**
	* @Title: generateDataSetFromTextFile
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return:Set<String>
	* @author: liuwei
	* @date: 2014年3月7日
	* Modification History:
	* Date  Author  Version  Description
	* ---------------------------------------------------------*
	* 2014年3月7日  liuwei v1.0.0   修改原因
	*/
	public Set<String> generateDataSetFromTextFile(String fileName) {
		Set<String> set = new HashSet<String>();
		BufferedReader br = null;
		FileReader reader = null;
		try {
			File file = new File(fileName);
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				String str = readLine.trim().toLowerCase();
				set.add(str);
			}
		} catch (IOException e) {

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return set;
	}

}
