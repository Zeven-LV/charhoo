package com.charhoo.os.utils;

/**
 * @Description: 轨迹分析
 * @author created by Ceruto
 * @date 下午2:58:06
 */
public class TraceScaner {
	
	//ACC格式1：ACC状态：ON/OFF
	public final static int ACC_FORMAR_1 = 1;
	//ACC格式1：ACC：ON/OFF
	public final static int ACC_FORMAR_2 = 2;
	/**
	 * 针对轨迹位置的分析。	<br>
	 * 北	0±22.5		<br>
	 * 东北	45±22.5		<br>
	 * 东	90±22.5		<br>
	 * 东南	135±22.5	<br>
	 * 南	180±22.5	<br>	
	 * 西南	225±22.5	<br>
	 * 西	270±22.5	<br>
	 * 西北	315±22.5	<br>
	 * @param dir
	 * @return
	 */
	public static String getDir(float dir){
		String dirStr = "";
		if(dir > 337.5f) dir = 337.5f - 360f;
		//将真实值左便宜22.5度。即原来的337.5为新的0度和360度的分割点。
		dir = dir + 22.5f;
		int v = (int)dir/45;
		switch (v) {
		case 0: dirStr = "北"; break;
		case 1: dirStr = "东北"; break;
		case 2: dirStr = "东"; break;
		case 3: dirStr = "东南"; break;
		case 4: dirStr = "南"; break;
		case 5: dirStr = "西南"; break;
		case 6: dirStr = "西"; break;
		case 7: dirStr = "西北"; break;

		default:
			break;
		}
		
		return dirStr;
	}
	
	/**
	 * 获得状态信息描述，目前仅需要ACC状态。
	 * @param status
	 * @return
	 */
	public static String getStatus(long src,int accFormat){
		StringBuffer result = new StringBuffer();
		int r = -1;
		
		//ACC状态。
		r = (int) (src & (long)Math.pow(2, 0));
		if(r == 1){
			if(ACC_FORMAR_1 == accFormat){
				result.append("ACC状态:ON");
			}
			if(ACC_FORMAR_2 == accFormat){
				result.append("ACC:ON");
			}
		}
		if(r == 0){
			if(ACC_FORMAR_1 == accFormat){
				result.append("ACC状态:OFF");
			}
			if(ACC_FORMAR_2 == accFormat){
				result.append("ACC:OFF");
			}
		}

		//获得定位卫星类型
		/*result.append(" | ");
		for(int i=18;i<22;i++){
			r = (int) (src & (long)Math.pow(2, i)) ;
			if(r > 0){
				switch (i) {
				case 18:
					result.append("GPS;");
					break;
				case 19:
					result.append("BeiDou;");
					break;
				case 20:
					result.append("GLONASS;");
					break;
				case 21:
					result.append("Galileo;");
					break;
				default:
					result.append("未知类型;");
					break;
				}
			}
			
			r = -1;
		}

		result.append(" | ");
		//是否定位
		r = (int) (src & 2);
		if(r == 2){
			result.append("已定位");
		}
		
		if(r == 0){
			result.append("未定位");
		}*/
		if(result.toString().equals("")) result.append("--");
		
		return result.toString();
	}
	
	/**
	 * 获得停靠点的间隔时长描述 a天b小时c分d秒
	 * @param interval
	 * @return
	 */
	public static String getStopInterval(long interval){
		String result = null;
		return result;
	}
	
}
