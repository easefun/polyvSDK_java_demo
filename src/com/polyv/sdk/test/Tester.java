package com.polyv.sdk.test;

import java.util.ArrayList;

import net.polyv.PolyvClient;
import net.polyv.ProgressImpl;

import com.polyv.sdk.PolyvSDKClient;
import com.polyv.sdk.Video;

public class Tester {
	public static void main(String[] args) {
		PolyvSDKClient client = PolyvSDKClient.getInstance();
		client.setReadtoken("nsJ7ZgQMN0-QsVkscukWt-qLfodxoDFm");
		client.setWritetoken("Y07Q4yopIVXN83n-MPoIlirBKmrMPJu0");

		// TODO Auto-generated method stub
		testUpload();
	}
	/**
	 * 断点续传上传实例
	 */
	public static void testResumableUpload(){
		//需要userid，writetoken,readtoken三个参数
		PolyvClient client = new PolyvClient("sl8da4jjbx",
				"nsJ7ZgQMN0-QsVkscukWt-qLfodxoDFm",
				"Y07Q4yopIVXN83n-MPoIlirBKmrMPJu0");
		client.setFilename("/Users/hhl/Downloads/test.avi");
		//ProgressImpl实现Progress接口，只print百分比
		//public void run(long offset, long max)，offset为当前上传完成的位移量，max是总量
		client.setProgress(new ProgressImpl());
		client.upload();
		System.out.println(client.getLocation());
		System.out.println(client.getJson());
	}
	public static void testGet() {
		try {
			Video v = PolyvSDKClient.getInstance().getVideo("sl8da4jjbx155339ddac9d2b62a76eee_s");
			System.out.println(v.getMp4_1());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUpload() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				Video v;
				try {
					v = PolyvSDKClient.getInstance().upload("/Users/hhl/Downloads/test.avi",
							"我的标题", "tag", "desc", 0);
					System.out.println(v.getFlv1());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		t.start();
		
		while(true){
			int percent = PolyvSDKClient.getInstance().getPercent();
			if(percent==100){
				break;
			}
			System.out.println("upload percent: " + percent + "%");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

	public static void testDeleteVideo() {
		try {

			boolean result = PolyvSDKClient.getInstance().deleteVideo(
					"sl8da4jjbx94ca49768ef78171849f78_s");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testListVideo() {
		try {
			ArrayList<Video> list = PolyvSDKClient.getInstance().getVideoList(1, 20);
			for (int i = 0; i < list.size(); i++) {
				Video v = list.get(i);
				System.out.println(v.getVid());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
