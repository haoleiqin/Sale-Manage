package com.univalsoft.gqzs.backup;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class CommonUtil {
	/**
	 * ��־��Ϣ
	 * 
	 * @param tag
	 *            ��־
	 * @param funcName
	 *            ����־�ĺ�����
	 * @param msg
	 *            ��־��Ϣ����
	 * @param type
	 *            ��־����
	 */
	public static void Log(String tag, String funcName, String msg, char type) {
		switch (type) {
		case 'e':
			Log.e(tag, funcName + "===>" + msg);
			break;
		case 'v':
			Log.v(tag, funcName + "===>" + msg);
			break;
		case 'i':
			Log.i(tag, funcName + "===>" + msg);
			break;
		case 'd':
			Log.d(tag, funcName + "===>" + msg);
			break;
		default:
			Log.d(tag, funcName + "===>" + msg);
			break;
		}
	}

	/**
	 * ��ʾ��ʾ��Ϣ
	 * 
	 * @param context
	 *            Ҫ��ʾ��Ϣ�ĵ�ǰʵ��
	 * @param msg
	 *            ��Ϣ����
	 */
	public static void Toast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

 

	 

	 

}
