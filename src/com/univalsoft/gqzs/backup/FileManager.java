package com.univalsoft.gqzs.backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileManager {
	public static final String COMMAND_BACKUP = "backup";
	public static final String COMMAND_RESTORE = "restore";
	public static final String COMMAND_ISCONTACT = "contact";
	private Context mContext;

	public FileManager(Context context) {
		this.mContext = context;
	}

 
	public int dbFileOperation(String... params) {
		// Ĭ��·���� /data/data/(����)/databases/*.db
		File dbFile = null;
		File backupDir = null;
		String command1 = params[1];// ��ʾ�ڶ�������
		if (command1.equals(COMMAND_ISCONTACT)) {
			dbFile = mContext.getDatabasePath("/data/data/com.univalsoft.gqzs/databases/sale.db");
			backupDir = new File(Environment.getExternalStorageDirectory(),
					"contactsBackup");
			Log.e("FileManager  ", Environment.getExternalStorageDirectory()

			+ "contactsBackup");
		} else {
			// System.out.println("û���ҵ����ݿ��ļ�");
			return -1;
		}

		
//		
//	and now	I have finished all the features funtion as your require, please check it
//		I'm very sorry to keep you waiting for such a long time
		
		
		
		if (!backupDir.exists()) {
			backupDir.mkdirs();// ���������ļ�
		}
		Log.e("�����ļ��������ǣ� ", dbFile.getName());
		System.out.println("�����ļ�����=====" + backupDir);
		File backup = new File(backupDir, dbFile.getName());
		String command2 = params[0];// ���ݵ�һ������
		// �ж��Ƿ�Ϊ���ñ������ݵķ���
		if (command2.equals(COMMAND_BACKUP)) {
			try {
				if (command1.equals(COMMAND_ISCONTACT)) {
					backup.createNewFile();
					fileCopy(dbFile, backup);
					return 0;
				} else {
					backup.createNewFile();// ����ٰ����ݿⱸ�ݵ�SD����
					fileCopy(dbFile, backup);
					return 3;
				}
			} catch (Exception e) {
				e.printStackTrace();
				// System.out.println("�����׳��쳣");
				return -1;
			}
		} else if (command2.equals(COMMAND_RESTORE)) {
			try {
				if (command1.equals(COMMAND_ISCONTACT)) {
					fileCopy(backup, dbFile);
					return 1;
				} else {
					fileCopy(backup, dbFile);// �Ȱ����ݿ��SD���ϻָ����ҵ����ݿ�
					return 4;
				}
			} catch (Exception e) {
				// System.out.println("��ԭ�׳��쳣");
				e.printStackTrace();
				return -1;
			}
		} else {
			return -1;
		}
	}

	private void fileCopy(File dbFile, File backup) throws IOException {
		FileChannel inChannel = new FileInputStream(dbFile).getChannel();
		FileChannel outChannel = new FileOutputStream(backup).getChannel();
		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (outChannel != null) {
				outChannel.close();
			}
		}
	}
}