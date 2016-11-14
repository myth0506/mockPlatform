package com.mockCommon.util.freeMarker;


	public interface IDataMaker <T>{
		
		/**
		 * 
		 *  根据传入的对象利用freeMarker替换生成文件
		 *
		 *   @param role
		 *   @return
		 *
		 **/
		public String generateData(String prefix,T role);
		
		/**
		 * 
		 *  根据传入的对象利用freeMarker替换生成数据流
		 *
		 *   @param role
		 *   @return
		 *
		 **/
		public String generateData2output(String prefix,T role);

}