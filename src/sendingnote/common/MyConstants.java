package sendingnote.common;

public class MyConstants {
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String DB_USER = "hr";
	public static final String DB_PASSWORD = "hr";
	
	public static final String PROTOCOL_COMMAND_USER_INSERT = "�⺻�������";
	public static final String PROTOCOL_COMMAND_NOTE_INSRT = "�޼������";
	public static final String PROTOCOL_COMMAND_LOG_IN = "�α���";
	public static final String PROTOCOL_COMMAND_LOG_OUT = "�α׾ƿ�";
	public static final String PROTOCOL_COMMAND_CHECK_USER_LIST = "���ӻ���";
	public static final String PROTOCOL_COMMAND_DELETE_MESSAGE = "�޼�������";
	public static final String PROTOCOL_COMMAND_UNREAD_MESSAGE = "���������޼���";
	public static final String PROTOCOL_COMMAND_ALL_MESSAGE = "�޼�����ü";
	public static final String PROTOCOL_COMMAND_ALL_READ_UNREAD = "�޼������¼���";
	public static final String PROTOCOL_COMMAND_SELECTED_MESSAGE = "�޼�����������";
	
	//------ ��
	public static final String PROTOCOL_COMMAND_PET_INSERT = "insert";
	public static final String PROTOCOL_COMMAND_PET_SEARCH = "search";
	public static final String PROTOCOL_COMMAND_PET_DELETE = "delete";
	public static final String PROTOCOL_COMMAND_PET_UPDATE = "update";
	public static final String PROTOCOL_COMMAND_PET_GETALL = "getAll";
	
	public static final String MY_SENT_NOTE_COLUMN_NAME_NOTE_ID = "ID";
	public static final String MY_SENT_NOTE_COLUMN_NAME_NOTE_TITLE = "Title";
	public static final String MY_SENT_NOTE_COLUMN_NAME_NOTE_DATE = "Date";
	public static final String MY_SENT_NOTE_COLUMN_NAME_NOTE_TO_USERS = "TO";
	
	public static final String[] MY_SENT_NOTE_COLUMN_NAMES = {
			MY_SENT_NOTE_COLUMN_NAME_NOTE_ID,
			MY_SENT_NOTE_COLUMN_NAME_NOTE_TITLE,
			MY_SENT_NOTE_COLUMN_NAME_NOTE_DATE,
			MY_SENT_NOTE_COLUMN_NAME_NOTE_TO_USERS
	};
}
