// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package talend_montauban.finaljobpart2_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: FinalJobPart2 Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class FinalJobPart2 implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "FinalJobPart2";
	private final String projectName = "TALEND_MONTAUBAN";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					FinalJobPart2.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(FinalJobPart2.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBCommit_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBCommit_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tPrejob_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tPrejob_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBConnection_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBConnection_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row45_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_5_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBCommit_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tPrejob_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBConnection_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct
			implements routines.system.IPersistableRow<copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idzone;

		public String getIdzone() {
			return this.idzone;
		}

		public String libelle;

		public String getLibelle() {
			return this.libelle;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idzone == null) ? 0 : this.idzone.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct other = (copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct) obj;

			if (this.idzone == null) {
				if (other.idzone != null)
					return false;

			} else if (!this.idzone.equals(other.idzone))

				return false;

			return true;
		}

		public void copyDataTo(copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct other) {

			other.idzone = this.idzone;
			other.libelle = this.libelle;

		}

		public void copyKeysDataTo(copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct other) {

			other.idzone = this.idzone;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idzone = readString(dis);

					this.libelle = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idzone = readString(dis);

					this.libelle = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idzone, dos);

				// String

				writeString(this.libelle, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idzone, dos);

				// String

				writeString(this.libelle, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idzone=" + idzone);
			sb.append(",libelle=" + libelle);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idzone, other.idzone);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row40Struct implements routines.system.IPersistableRow<row40Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String libelle;

		public String getLibelle() {
			return this.libelle;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.libelle = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.libelle = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.libelle, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.libelle, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",libelle=" + libelle);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row40Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row40Struct row40 = new row40Struct();
				copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct copyOfcopyOfcopyOfcopyOfcopyOfmain_2 = new copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0,
							"copyOfcopyOfcopyOfcopyOfcopyOfmain_2");
				}

				int tos_count_tDBOutput_1 = 0;

				String dbschema_tDBOutput_1 = null;
				dbschema_tDBOutput_1 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_1 = null;
				if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
					tableName_tDBOutput_1 = ("zone");
				} else {
					tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "\".\"" + ("zone");
				}

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				boolean whetherReject_tDBOutput_1 = false;

				java.sql.Connection conn_tDBOutput_1 = null;
				String dbUser_tDBOutput_1 = null;

				conn_tDBOutput_1 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int batchSize_tDBOutput_1 = 10000;
				int batchSizeCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;
				try (java.sql.Statement stmtClear_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					stmtClear_tDBOutput_1.executeUpdate("DELETE FROM \"" + tableName_tDBOutput_1 + "\"");
				}
				String insert_tDBOutput_1 = "INSERT INTO \"" + tableName_tDBOutput_1
						+ "\" (\"idzone\",\"libelle\") VALUES (?,?)";

				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row40");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct copyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp = new copyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/DEV/Data/Base Montauban/param/zones.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/DEV/Data/Base Montauban/param/zones.csv", "ISO-8859-15", ";", "\n", true, 1, 0,
								limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row40 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row40 = new row40Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							columnIndexWithD_tFileInputDelimited_1 = 0;

							row40.code = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row40.libelle = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row40 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row40"
						if (row40 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row40"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								copyOfcopyOfcopyOfcopyOfcopyOfmain_2 = null;

// # Output table : 'copyOfcopyOfcopyOfcopyOfcopyOfmain_2'
								copyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.idzone = row40.code;
								copyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle = row40.libelle;
								copyOfcopyOfcopyOfcopyOfcopyOfmain_2 = copyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "copyOfcopyOfcopyOfcopyOfcopyOfmain_2"
							if (copyOfcopyOfcopyOfcopyOfcopyOfmain_2 != null) {

								/**
								 * [tDBOutput_1 main ] start
								 */

								currentComponent = "tDBOutput_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "copyOfcopyOfcopyOfcopyOfcopyOfmain_2"

									);
								}

								whetherReject_tDBOutput_1 = false;
								if (copyOfcopyOfcopyOfcopyOfcopyOfmain_2.idzone == null) {
									pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(1, copyOfcopyOfcopyOfcopyOfcopyOfmain_2.idzone);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle == null) {
									pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(2, copyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle);
								}

								pstmt_tDBOutput_1.addBatch();
								nb_line_tDBOutput_1++;

								batchSizeCounter_tDBOutput_1++;

								if ((batchSize_tDBOutput_1 > 0)
										&& (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1)) {
									try {
										int countSum_tDBOutput_1 = 0;

										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
													: countEach_tDBOutput_1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

										batchSizeCounter_tDBOutput_1 = 0;
									} catch (java.sql.BatchUpdateException e_tDBOutput_1) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e_tDBOutput_1.getMessage());
										java.sql.SQLException ne_tDBOutput_1 = e_tDBOutput_1.getNextException(),
												sqle_tDBOutput_1 = null;
										String errormessage_tDBOutput_1;
										if (ne_tDBOutput_1 != null) {
											// build new exception to provide the original cause
											sqle_tDBOutput_1 = new java.sql.SQLException(
													e_tDBOutput_1.getMessage() + "\ncaused by: "
															+ ne_tDBOutput_1.getMessage(),
													ne_tDBOutput_1.getSQLState(), ne_tDBOutput_1.getErrorCode(),
													ne_tDBOutput_1);
											errormessage_tDBOutput_1 = sqle_tDBOutput_1.getMessage();
										} else {
											errormessage_tDBOutput_1 = e_tDBOutput_1.getMessage();
										}

										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : e_tDBOutput_1.getUpdateCounts()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
													: countEach_tDBOutput_1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

										System.err.println(errormessage_tDBOutput_1);

									}
								}

								tos_count_tDBOutput_1++;

								/**
								 * [tDBOutput_1 main ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_end ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_end ] stop
								 */

							} // End of branch "copyOfcopyOfcopyOfcopyOfcopyOfmain_2"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row40"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/DEV/Data/Base Montauban/param/zones.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row40");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					int countSum_tDBOutput_1 = 0;
					if (pstmt_tDBOutput_1 != null && batchSizeCounter_tDBOutput_1 > 0) {

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					}

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

				} catch (java.sql.BatchUpdateException e_tDBOutput_1) {
					globalMap.put("tDBOutput_1_ERROR_MESSAGE", e_tDBOutput_1.getMessage());
					java.sql.SQLException ne_tDBOutput_1 = e_tDBOutput_1.getNextException(), sqle_tDBOutput_1 = null;
					String errormessage_tDBOutput_1;
					if (ne_tDBOutput_1 != null) {
						// build new exception to provide the original cause
						sqle_tDBOutput_1 = new java.sql.SQLException(
								e_tDBOutput_1.getMessage() + "\ncaused by: " + ne_tDBOutput_1.getMessage(),
								ne_tDBOutput_1.getSQLState(), ne_tDBOutput_1.getErrorCode(), ne_tDBOutput_1);
						errormessage_tDBOutput_1 = sqle_tDBOutput_1.getMessage();
					} else {
						errormessage_tDBOutput_1 = e_tDBOutput_1.getMessage();
					}

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e_tDBOutput_1.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(errormessage_tDBOutput_1);

				}

				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");
				}
				resourceMap.put("statementClosed_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "copyOfcopyOfcopyOfcopyOfcopyOfmain_2");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk5", 0, "ok");
			}

			tFileInputDelimited_2Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
					java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
					if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_1")) != null) {
						pstmtToClose_tDBOutput_1.close();
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct
			implements routines.system.IPersistableRow<copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idtournee;

		public String getIdtournee() {
			return this.idtournee;
		}

		public String libelle_tournee;

		public String getLibelle_tournee() {
			return this.libelle_tournee;
		}

		public String idsecteur;

		public String getIdsecteur() {
			return this.idsecteur;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idtournee == null) ? 0 : this.idtournee.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct other = (copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct) obj;

			if (this.idtournee == null) {
				if (other.idtournee != null)
					return false;

			} else if (!this.idtournee.equals(other.idtournee))

				return false;

			return true;
		}

		public void copyDataTo(copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct other) {

			other.idtournee = this.idtournee;
			other.libelle_tournee = this.libelle_tournee;
			other.idsecteur = this.idsecteur;

		}

		public void copyKeysDataTo(copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct other) {

			other.idtournee = this.idtournee;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idtournee = readString(dis);

					this.libelle_tournee = readString(dis);

					this.idsecteur = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idtournee = readString(dis);

					this.libelle_tournee = readString(dis);

					this.idsecteur = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idtournee, dos);

				// String

				writeString(this.libelle_tournee, dos);

				// String

				writeString(this.idsecteur, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idtournee, dos);

				// String

				writeString(this.libelle_tournee, dos);

				// String

				writeString(this.idsecteur, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idtournee=" + idtournee);
			sb.append(",libelle_tournee=" + libelle_tournee);
			sb.append(",idsecteur=" + idsecteur);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idtournee, other.idtournee);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row41Struct implements routines.system.IPersistableRow<row41Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];

		public String CODE_SECTEUR;

		public String getCODE_SECTEUR() {
			return this.CODE_SECTEUR;
		}

		public String CODE_TOURNEE;

		public String getCODE_TOURNEE() {
			return this.CODE_TOURNEE;
		}

		public String LIBELLE_TOURNEE;

		public String getLIBELLE_TOURNEE() {
			return this.LIBELLE_TOURNEE;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.CODE_SECTEUR = readString(dis);

					this.CODE_TOURNEE = readString(dis);

					this.LIBELLE_TOURNEE = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.CODE_SECTEUR = readString(dis);

					this.CODE_TOURNEE = readString(dis);

					this.LIBELLE_TOURNEE = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CODE_SECTEUR, dos);

				// String

				writeString(this.CODE_TOURNEE, dos);

				// String

				writeString(this.LIBELLE_TOURNEE, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CODE_SECTEUR, dos);

				// String

				writeString(this.CODE_TOURNEE, dos);

				// String

				writeString(this.LIBELLE_TOURNEE, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CODE_SECTEUR=" + CODE_SECTEUR);
			sb.append(",CODE_TOURNEE=" + CODE_TOURNEE);
			sb.append(",LIBELLE_TOURNEE=" + LIBELLE_TOURNEE);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row41Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row41Struct row41 = new row41Struct();
				copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0 = new copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct();

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0,
							"copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0");
				}

				int tos_count_tDBOutput_2 = 0;

				String dbschema_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_2 = null;
				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = ("tournee");
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "\".\"" + ("tournee");
				}

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;
				int rejectedCount_tDBOutput_2 = 0;

				boolean whetherReject_tDBOutput_2 = false;

				java.sql.Connection conn_tDBOutput_2 = null;
				String dbUser_tDBOutput_2 = null;

				conn_tDBOutput_2 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int batchSize_tDBOutput_2 = 10000;
				int batchSizeCounter_tDBOutput_2 = 0;

				int count_tDBOutput_2 = 0;
				try (java.sql.Statement stmtClear_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
					stmtClear_tDBOutput_2.executeUpdate("DELETE FROM \"" + tableName_tDBOutput_2 + "\"");
				}
				String insert_tDBOutput_2 = "INSERT INTO \"" + tableName_tDBOutput_2
						+ "\" (\"idtournee\",\"libelle_tournee\",\"idsecteur\") VALUES (?,?,?)";

				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				ok_Hash.put("tMap_2", false);
				start_Hash.put("tMap_2", System.currentTimeMillis());

				currentComponent = "tMap_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row41");
				}

				int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp = new copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try {

					Object filename_tFileInputDelimited_2 = "C:/DEV/Data/Base Montauban/param/tournees.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"C:/DEV/Data/Base Montauban/param/tournees.csv", "ISO-8859-15", ";", "\n", true, 1, 0,
								limit_tFileInputDelimited_2, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_2 != null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						row41 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row41 = new row41Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							columnIndexWithD_tFileInputDelimited_2 = 0;

							row41.CODE_SECTEUR = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 1;

							row41.CODE_TOURNEE = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 2;

							row41.LIBELLE_TOURNEE = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							System.err.println(e.getMessage());
							row41 = null;

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row41"
						if (row41 != null) {

							/**
							 * [tMap_2 main ] start
							 */

							currentComponent = "tMap_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row41"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_2 = false;
							boolean mainRowRejected_tMap_2 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
								// ###############################
								// # Output tables

								copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0 = null;

// # Output table : 'copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0'
								copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.idtournee = row41.CODE_SECTEUR.equals("B")
										? new String("0") + row41.CODE_TOURNEE
										: row41.CODE_SECTEUR + row41.CODE_TOURNEE;
								copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.libelle_tournee = row41.LIBELLE_TOURNEE;
								copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.idsecteur = row41.CODE_SECTEUR.equals("B")
										? new String("0")
										: row41.CODE_SECTEUR;
								copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0 = copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_2 = false;

							tos_count_tMap_2++;

							/**
							 * [tMap_2 main ] stop
							 */

							/**
							 * [tMap_2 process_data_begin ] start
							 */

							currentComponent = "tMap_2";

							/**
							 * [tMap_2 process_data_begin ] stop
							 */
// Start of branch "copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0"
							if (copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0 != null) {

								/**
								 * [tDBOutput_2 main ] start
								 */

								currentComponent = "tDBOutput_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0"

									);
								}

								whetherReject_tDBOutput_2 = false;
								if (copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.idtournee == null) {
									pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_2.setString(1, copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.idtournee);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.libelle_tournee == null) {
									pstmt_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_2.setString(2,
											copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.libelle_tournee);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.idsecteur == null) {
									pstmt_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_2.setString(3, copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.idsecteur);
								}

								pstmt_tDBOutput_2.addBatch();
								nb_line_tDBOutput_2++;

								batchSizeCounter_tDBOutput_2++;

								if ((batchSize_tDBOutput_2 > 0)
										&& (batchSize_tDBOutput_2 <= batchSizeCounter_tDBOutput_2)) {
									try {
										int countSum_tDBOutput_2 = 0;

										for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
											countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0
													: countEach_tDBOutput_2);
										}
										rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

										insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

										batchSizeCounter_tDBOutput_2 = 0;
									} catch (java.sql.BatchUpdateException e_tDBOutput_2) {
										globalMap.put("tDBOutput_2_ERROR_MESSAGE", e_tDBOutput_2.getMessage());
										java.sql.SQLException ne_tDBOutput_2 = e_tDBOutput_2.getNextException(),
												sqle_tDBOutput_2 = null;
										String errormessage_tDBOutput_2;
										if (ne_tDBOutput_2 != null) {
											// build new exception to provide the original cause
											sqle_tDBOutput_2 = new java.sql.SQLException(
													e_tDBOutput_2.getMessage() + "\ncaused by: "
															+ ne_tDBOutput_2.getMessage(),
													ne_tDBOutput_2.getSQLState(), ne_tDBOutput_2.getErrorCode(),
													ne_tDBOutput_2);
											errormessage_tDBOutput_2 = sqle_tDBOutput_2.getMessage();
										} else {
											errormessage_tDBOutput_2 = e_tDBOutput_2.getMessage();
										}

										int countSum_tDBOutput_2 = 0;
										for (int countEach_tDBOutput_2 : e_tDBOutput_2.getUpdateCounts()) {
											countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0
													: countEach_tDBOutput_2);
										}
										rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

										insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

										System.err.println(errormessage_tDBOutput_2);

									}
								}

								tos_count_tDBOutput_2++;

								/**
								 * [tDBOutput_2 main ] stop
								 */

								/**
								 * [tDBOutput_2 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_2";

								/**
								 * [tDBOutput_2 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_2 process_data_end ] start
								 */

								currentComponent = "tDBOutput_2";

								/**
								 * [tDBOutput_2 process_data_end ] stop
								 */

							} // End of branch "copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0"

							/**
							 * [tMap_2 process_data_end ] start
							 */

							currentComponent = "tMap_2";

							/**
							 * [tMap_2 process_data_end ] stop
							 */

						} // End of branch "row41"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

					}
				} finally {
					if (!((Object) ("C:/DEV/Data/Base Montauban/param/tournees.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row41");
				}

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					int countSum_tDBOutput_2 = 0;
					if (pstmt_tDBOutput_2 != null && batchSizeCounter_tDBOutput_2 > 0) {

						for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
							countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
						}
						rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

					}

					insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

				} catch (java.sql.BatchUpdateException e_tDBOutput_2) {
					globalMap.put("tDBOutput_2_ERROR_MESSAGE", e_tDBOutput_2.getMessage());
					java.sql.SQLException ne_tDBOutput_2 = e_tDBOutput_2.getNextException(), sqle_tDBOutput_2 = null;
					String errormessage_tDBOutput_2;
					if (ne_tDBOutput_2 != null) {
						// build new exception to provide the original cause
						sqle_tDBOutput_2 = new java.sql.SQLException(
								e_tDBOutput_2.getMessage() + "\ncaused by: " + ne_tDBOutput_2.getMessage(),
								ne_tDBOutput_2.getSQLState(), ne_tDBOutput_2.getErrorCode(), ne_tDBOutput_2);
						errormessage_tDBOutput_2 = sqle_tDBOutput_2.getMessage();
					} else {
						errormessage_tDBOutput_2 = e_tDBOutput_2.getMessage();
					}

					int countSum_tDBOutput_2 = 0;
					for (int countEach_tDBOutput_2 : e_tDBOutput_2.getUpdateCounts()) {
						countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
					}
					rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

					insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

					System.err.println(errormessage_tDBOutput_2);

				}

				if (pstmt_tDBOutput_2 != null) {

					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");
				}
				resourceMap.put("statementClosed_tDBOutput_2", true);

				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "copyOfcopyOfcopyOfcopyOfcopyOfmain_2_0");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_2:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk6", 0, "ok");
			}

			tFileInputDelimited_3Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
					java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
					if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_2")) != null) {
						pstmtToClose_tDBOutput_2.close();
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public static class copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct
			implements routines.system.IPersistableRow<copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String idglossaire;

		public String getIdglossaire() {
			return this.idglossaire;
		}

		public String libelle_1;

		public String getLibelle_1() {
			return this.libelle_1;
		}

		public String libelle_10;

		public String getLibelle_10() {
			return this.libelle_10;
		}

		public String libelle_11;

		public String getLibelle_11() {
			return this.libelle_11;
		}

		public String libelle_12;

		public String getLibelle_12() {
			return this.libelle_12;
		}

		public String libelle_2;

		public String getLibelle_2() {
			return this.libelle_2;
		}

		public String libelle_3;

		public String getLibelle_3() {
			return this.libelle_3;
		}

		public String libelle_4;

		public String getLibelle_4() {
			return this.libelle_4;
		}

		public String libelle_5;

		public String getLibelle_5() {
			return this.libelle_5;
		}

		public String libelle_6;

		public String getLibelle_6() {
			return this.libelle_6;
		}

		public String libelle_7;

		public String getLibelle_7() {
			return this.libelle_7;
		}

		public String libelle_8;

		public String getLibelle_8() {
			return this.libelle_8;
		}

		public String libelle_9;

		public String getLibelle_9() {
			return this.libelle_9;
		}

		public String idcli;

		public String getIdcli() {
			return this.idcli;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.idglossaire == null) ? 0 : this.idglossaire.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct other = (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct) obj;

			if (this.idglossaire == null) {
				if (other.idglossaire != null)
					return false;

			} else if (!this.idglossaire.equals(other.idglossaire))

				return false;

			return true;
		}

		public void copyDataTo(copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct other) {

			other.idglossaire = this.idglossaire;
			other.libelle_1 = this.libelle_1;
			other.libelle_10 = this.libelle_10;
			other.libelle_11 = this.libelle_11;
			other.libelle_12 = this.libelle_12;
			other.libelle_2 = this.libelle_2;
			other.libelle_3 = this.libelle_3;
			other.libelle_4 = this.libelle_4;
			other.libelle_5 = this.libelle_5;
			other.libelle_6 = this.libelle_6;
			other.libelle_7 = this.libelle_7;
			other.libelle_8 = this.libelle_8;
			other.libelle_9 = this.libelle_9;
			other.idcli = this.idcli;

		}

		public void copyKeysDataTo(copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct other) {

			other.idglossaire = this.idglossaire;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idglossaire = readString(dis);

					this.libelle_1 = readString(dis);

					this.libelle_10 = readString(dis);

					this.libelle_11 = readString(dis);

					this.libelle_12 = readString(dis);

					this.libelle_2 = readString(dis);

					this.libelle_3 = readString(dis);

					this.libelle_4 = readString(dis);

					this.libelle_5 = readString(dis);

					this.libelle_6 = readString(dis);

					this.libelle_7 = readString(dis);

					this.libelle_8 = readString(dis);

					this.libelle_9 = readString(dis);

					this.idcli = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idglossaire = readString(dis);

					this.libelle_1 = readString(dis);

					this.libelle_10 = readString(dis);

					this.libelle_11 = readString(dis);

					this.libelle_12 = readString(dis);

					this.libelle_2 = readString(dis);

					this.libelle_3 = readString(dis);

					this.libelle_4 = readString(dis);

					this.libelle_5 = readString(dis);

					this.libelle_6 = readString(dis);

					this.libelle_7 = readString(dis);

					this.libelle_8 = readString(dis);

					this.libelle_9 = readString(dis);

					this.idcli = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.idglossaire, dos);

				// String

				writeString(this.libelle_1, dos);

				// String

				writeString(this.libelle_10, dos);

				// String

				writeString(this.libelle_11, dos);

				// String

				writeString(this.libelle_12, dos);

				// String

				writeString(this.libelle_2, dos);

				// String

				writeString(this.libelle_3, dos);

				// String

				writeString(this.libelle_4, dos);

				// String

				writeString(this.libelle_5, dos);

				// String

				writeString(this.libelle_6, dos);

				// String

				writeString(this.libelle_7, dos);

				// String

				writeString(this.libelle_8, dos);

				// String

				writeString(this.libelle_9, dos);

				// String

				writeString(this.idcli, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.idglossaire, dos);

				// String

				writeString(this.libelle_1, dos);

				// String

				writeString(this.libelle_10, dos);

				// String

				writeString(this.libelle_11, dos);

				// String

				writeString(this.libelle_12, dos);

				// String

				writeString(this.libelle_2, dos);

				// String

				writeString(this.libelle_3, dos);

				// String

				writeString(this.libelle_4, dos);

				// String

				writeString(this.libelle_5, dos);

				// String

				writeString(this.libelle_6, dos);

				// String

				writeString(this.libelle_7, dos);

				// String

				writeString(this.libelle_8, dos);

				// String

				writeString(this.libelle_9, dos);

				// String

				writeString(this.idcli, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idglossaire=" + idglossaire);
			sb.append(",libelle_1=" + libelle_1);
			sb.append(",libelle_10=" + libelle_10);
			sb.append(",libelle_11=" + libelle_11);
			sb.append(",libelle_12=" + libelle_12);
			sb.append(",libelle_2=" + libelle_2);
			sb.append(",libelle_3=" + libelle_3);
			sb.append(",libelle_4=" + libelle_4);
			sb.append(",libelle_5=" + libelle_5);
			sb.append(",libelle_6=" + libelle_6);
			sb.append(",libelle_7=" + libelle_7);
			sb.append(",libelle_8=" + libelle_8);
			sb.append(",libelle_9=" + libelle_9);
			sb.append(",idcli=" + idcli);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idglossaire, other.idglossaire);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row42Struct implements routines.system.IPersistableRow<row42Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];

		public String GLOSCLI;

		public String getGLOSCLI() {
			return this.GLOSCLI;
		}

		public String GLOSREF;

		public String getGLOSREF() {
			return this.GLOSREF;
		}

		public String GLOSLIB01;

		public String getGLOSLIB01() {
			return this.GLOSLIB01;
		}

		public String GLOSLIB02;

		public String getGLOSLIB02() {
			return this.GLOSLIB02;
		}

		public String GLOSLIB03;

		public String getGLOSLIB03() {
			return this.GLOSLIB03;
		}

		public String GLOSLIB04;

		public String getGLOSLIB04() {
			return this.GLOSLIB04;
		}

		public String GLOSLIB05;

		public String getGLOSLIB05() {
			return this.GLOSLIB05;
		}

		public String GLOSLIB06;

		public String getGLOSLIB06() {
			return this.GLOSLIB06;
		}

		public String GLOSLIB07;

		public String getGLOSLIB07() {
			return this.GLOSLIB07;
		}

		public String GLOSLIB08;

		public String getGLOSLIB08() {
			return this.GLOSLIB08;
		}

		public String GLOSLIB09;

		public String getGLOSLIB09() {
			return this.GLOSLIB09;
		}

		public String GLOSLIB10;

		public String getGLOSLIB10() {
			return this.GLOSLIB10;
		}

		public String GLOSLIB11;

		public String getGLOSLIB11() {
			return this.GLOSLIB11;
		}

		public String GLOSLIB12;

		public String getGLOSLIB12() {
			return this.GLOSLIB12;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.GLOSCLI = readString(dis);

					this.GLOSREF = readString(dis);

					this.GLOSLIB01 = readString(dis);

					this.GLOSLIB02 = readString(dis);

					this.GLOSLIB03 = readString(dis);

					this.GLOSLIB04 = readString(dis);

					this.GLOSLIB05 = readString(dis);

					this.GLOSLIB06 = readString(dis);

					this.GLOSLIB07 = readString(dis);

					this.GLOSLIB08 = readString(dis);

					this.GLOSLIB09 = readString(dis);

					this.GLOSLIB10 = readString(dis);

					this.GLOSLIB11 = readString(dis);

					this.GLOSLIB12 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.GLOSCLI = readString(dis);

					this.GLOSREF = readString(dis);

					this.GLOSLIB01 = readString(dis);

					this.GLOSLIB02 = readString(dis);

					this.GLOSLIB03 = readString(dis);

					this.GLOSLIB04 = readString(dis);

					this.GLOSLIB05 = readString(dis);

					this.GLOSLIB06 = readString(dis);

					this.GLOSLIB07 = readString(dis);

					this.GLOSLIB08 = readString(dis);

					this.GLOSLIB09 = readString(dis);

					this.GLOSLIB10 = readString(dis);

					this.GLOSLIB11 = readString(dis);

					this.GLOSLIB12 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.GLOSCLI, dos);

				// String

				writeString(this.GLOSREF, dos);

				// String

				writeString(this.GLOSLIB01, dos);

				// String

				writeString(this.GLOSLIB02, dos);

				// String

				writeString(this.GLOSLIB03, dos);

				// String

				writeString(this.GLOSLIB04, dos);

				// String

				writeString(this.GLOSLIB05, dos);

				// String

				writeString(this.GLOSLIB06, dos);

				// String

				writeString(this.GLOSLIB07, dos);

				// String

				writeString(this.GLOSLIB08, dos);

				// String

				writeString(this.GLOSLIB09, dos);

				// String

				writeString(this.GLOSLIB10, dos);

				// String

				writeString(this.GLOSLIB11, dos);

				// String

				writeString(this.GLOSLIB12, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.GLOSCLI, dos);

				// String

				writeString(this.GLOSREF, dos);

				// String

				writeString(this.GLOSLIB01, dos);

				// String

				writeString(this.GLOSLIB02, dos);

				// String

				writeString(this.GLOSLIB03, dos);

				// String

				writeString(this.GLOSLIB04, dos);

				// String

				writeString(this.GLOSLIB05, dos);

				// String

				writeString(this.GLOSLIB06, dos);

				// String

				writeString(this.GLOSLIB07, dos);

				// String

				writeString(this.GLOSLIB08, dos);

				// String

				writeString(this.GLOSLIB09, dos);

				// String

				writeString(this.GLOSLIB10, dos);

				// String

				writeString(this.GLOSLIB11, dos);

				// String

				writeString(this.GLOSLIB12, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("GLOSCLI=" + GLOSCLI);
			sb.append(",GLOSREF=" + GLOSREF);
			sb.append(",GLOSLIB01=" + GLOSLIB01);
			sb.append(",GLOSLIB02=" + GLOSLIB02);
			sb.append(",GLOSLIB03=" + GLOSLIB03);
			sb.append(",GLOSLIB04=" + GLOSLIB04);
			sb.append(",GLOSLIB05=" + GLOSLIB05);
			sb.append(",GLOSLIB06=" + GLOSLIB06);
			sb.append(",GLOSLIB07=" + GLOSLIB07);
			sb.append(",GLOSLIB08=" + GLOSLIB08);
			sb.append(",GLOSLIB09=" + GLOSLIB09);
			sb.append(",GLOSLIB10=" + GLOSLIB10);
			sb.append(",GLOSLIB11=" + GLOSLIB11);
			sb.append(",GLOSLIB12=" + GLOSLIB12);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row42Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row42Struct row42 = new row42Struct();
				copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2 = new copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct();

				/**
				 * [tDBOutput_3 begin ] start
				 */

				ok_Hash.put("tDBOutput_3", false);
				start_Hash.put("tDBOutput_3", System.currentTimeMillis());

				currentComponent = "tDBOutput_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0,
							"copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2");
				}

				int tos_count_tDBOutput_3 = 0;

				String dbschema_tDBOutput_3 = null;
				dbschema_tDBOutput_3 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_3 = null;
				if (dbschema_tDBOutput_3 == null || dbschema_tDBOutput_3.trim().length() == 0) {
					tableName_tDBOutput_3 = ("glossaire");
				} else {
					tableName_tDBOutput_3 = dbschema_tDBOutput_3 + "\".\"" + ("glossaire");
				}

				int nb_line_tDBOutput_3 = 0;
				int nb_line_update_tDBOutput_3 = 0;
				int nb_line_inserted_tDBOutput_3 = 0;
				int nb_line_deleted_tDBOutput_3 = 0;
				int nb_line_rejected_tDBOutput_3 = 0;

				int deletedCount_tDBOutput_3 = 0;
				int updatedCount_tDBOutput_3 = 0;
				int insertedCount_tDBOutput_3 = 0;
				int rowsToCommitCount_tDBOutput_3 = 0;
				int rejectedCount_tDBOutput_3 = 0;

				boolean whetherReject_tDBOutput_3 = false;

				java.sql.Connection conn_tDBOutput_3 = null;
				String dbUser_tDBOutput_3 = null;

				conn_tDBOutput_3 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int batchSize_tDBOutput_3 = 10000;
				int batchSizeCounter_tDBOutput_3 = 0;

				int count_tDBOutput_3 = 0;
				try (java.sql.Statement stmtClear_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
					stmtClear_tDBOutput_3.executeUpdate("DELETE FROM \"" + tableName_tDBOutput_3 + "\"");
				}
				String insert_tDBOutput_3 = "INSERT INTO \"" + tableName_tDBOutput_3
						+ "\" (\"idglossaire\",\"libelle_1\",\"libelle_10\",\"libelle_11\",\"libelle_12\",\"libelle_2\",\"libelle_3\",\"libelle_4\",\"libelle_5\",\"libelle_6\",\"libelle_7\",\"libelle_8\",\"libelle_9\",\"idcli\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				java.sql.PreparedStatement pstmt_tDBOutput_3 = conn_tDBOutput_3.prepareStatement(insert_tDBOutput_3);
				resourceMap.put("pstmt_tDBOutput_3", pstmt_tDBOutput_3);

				/**
				 * [tDBOutput_3 begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				ok_Hash.put("tMap_3", false);
				start_Hash.put("tMap_3", System.currentTimeMillis());

				currentComponent = "tMap_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row42");
				}

				int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp = new copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2Struct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_3", false);
				start_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_3";

				int tos_count_tFileInputDelimited_3 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_3 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_3 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_3 = null;
				int limit_tFileInputDelimited_3 = -1;
				try {

					Object filename_tFileInputDelimited_3 = "C:/DEV/Data/Base Montauban/glossaire.csv";
					if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_3 = 0, random_value_tFileInputDelimited_3 = -1;
						if (footer_value_tFileInputDelimited_3 > 0 || random_value_tFileInputDelimited_3 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_3 = new org.talend.fileprocess.FileInputDelimited(
								"C:/DEV/Data/Base Montauban/glossaire.csv", "ISO-8859-15", ";", "\n", true, 1, 0,
								limit_tFileInputDelimited_3, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_3 != null && fid_tFileInputDelimited_3.nextRecord()) {
						rowstate_tFileInputDelimited_3.reset();

						row42 = null;

						boolean whetherReject_tFileInputDelimited_3 = false;
						row42 = new row42Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_3 = 0;

							columnIndexWithD_tFileInputDelimited_3 = 0;

							row42.GLOSCLI = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 1;

							row42.GLOSREF = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 2;

							row42.GLOSLIB01 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 3;

							row42.GLOSLIB02 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 4;

							row42.GLOSLIB03 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 5;

							row42.GLOSLIB04 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 6;

							row42.GLOSLIB05 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 7;

							row42.GLOSLIB06 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 8;

							row42.GLOSLIB07 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 9;

							row42.GLOSLIB08 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 10;

							row42.GLOSLIB09 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 11;

							row42.GLOSLIB10 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 12;

							row42.GLOSLIB11 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 13;

							row42.GLOSLIB12 = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							if (rowstate_tFileInputDelimited_3.getException() != null) {
								throw rowstate_tFileInputDelimited_3.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_3 = true;

							System.err.println(e.getMessage());
							row42 = null;

						}

						/**
						 * [tFileInputDelimited_3 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_3 main ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						tos_count_tFileInputDelimited_3++;

						/**
						 * [tFileInputDelimited_3 main ] stop
						 */

						/**
						 * [tFileInputDelimited_3 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_begin ] stop
						 */
// Start of branch "row42"
						if (row42 != null) {

							/**
							 * [tMap_3 main ] start
							 */

							currentComponent = "tMap_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row42"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_3 = false;
							boolean mainRowRejected_tMap_3 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
								// ###############################
								// # Output tables

								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2 = null;

// # Output table : 'copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2'
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.idglossaire = row42.GLOSREF;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_1 = row42.GLOSLIB01;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_10 = row42.GLOSLIB10;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_11 = row42.GLOSLIB11;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_12 = row42.GLOSLIB12;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_2 = row42.GLOSLIB02;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_3 = row42.GLOSLIB03;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_4 = row42.GLOSLIB04;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_5 = row42.GLOSLIB05;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_6 = row42.GLOSLIB06;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_7 = row42.GLOSLIB07;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_8 = row42.GLOSLIB08;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.libelle_9 = row42.GLOSLIB09;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp.idcli = row42.GLOSCLI;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2 = copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_3 = false;

							tos_count_tMap_3++;

							/**
							 * [tMap_3 main ] stop
							 */

							/**
							 * [tMap_3 process_data_begin ] start
							 */

							currentComponent = "tMap_3";

							/**
							 * [tMap_3 process_data_begin ] stop
							 */
// Start of branch "copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2"
							if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2 != null) {

								/**
								 * [tDBOutput_3 main ] start
								 */

								currentComponent = "tDBOutput_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2"

									);
								}

								whetherReject_tDBOutput_3 = false;
								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.idglossaire == null) {
									pstmt_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(1,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.idglossaire);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_1 == null) {
									pstmt_tDBOutput_3.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(2,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_1);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_10 == null) {
									pstmt_tDBOutput_3.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(3,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_10);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_11 == null) {
									pstmt_tDBOutput_3.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(4,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_11);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_12 == null) {
									pstmt_tDBOutput_3.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(5,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_12);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_2 == null) {
									pstmt_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(6,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_2);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_3 == null) {
									pstmt_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(7,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_3);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_4 == null) {
									pstmt_tDBOutput_3.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(8,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_4);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_5 == null) {
									pstmt_tDBOutput_3.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(9,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_5);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_6 == null) {
									pstmt_tDBOutput_3.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(10,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_6);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_7 == null) {
									pstmt_tDBOutput_3.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(11,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_7);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_8 == null) {
									pstmt_tDBOutput_3.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(12,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_8);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_9 == null) {
									pstmt_tDBOutput_3.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(13,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.libelle_9);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.idcli == null) {
									pstmt_tDBOutput_3.setNull(14, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_3.setString(14, copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2.idcli);
								}

								pstmt_tDBOutput_3.addBatch();
								nb_line_tDBOutput_3++;

								batchSizeCounter_tDBOutput_3++;

								if ((batchSize_tDBOutput_3 > 0)
										&& (batchSize_tDBOutput_3 <= batchSizeCounter_tDBOutput_3)) {
									try {
										int countSum_tDBOutput_3 = 0;

										for (int countEach_tDBOutput_3 : pstmt_tDBOutput_3.executeBatch()) {
											countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0
													: countEach_tDBOutput_3);
										}
										rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;

										insertedCount_tDBOutput_3 += countSum_tDBOutput_3;

										batchSizeCounter_tDBOutput_3 = 0;
									} catch (java.sql.BatchUpdateException e_tDBOutput_3) {
										globalMap.put("tDBOutput_3_ERROR_MESSAGE", e_tDBOutput_3.getMessage());
										java.sql.SQLException ne_tDBOutput_3 = e_tDBOutput_3.getNextException(),
												sqle_tDBOutput_3 = null;
										String errormessage_tDBOutput_3;
										if (ne_tDBOutput_3 != null) {
											// build new exception to provide the original cause
											sqle_tDBOutput_3 = new java.sql.SQLException(
													e_tDBOutput_3.getMessage() + "\ncaused by: "
															+ ne_tDBOutput_3.getMessage(),
													ne_tDBOutput_3.getSQLState(), ne_tDBOutput_3.getErrorCode(),
													ne_tDBOutput_3);
											errormessage_tDBOutput_3 = sqle_tDBOutput_3.getMessage();
										} else {
											errormessage_tDBOutput_3 = e_tDBOutput_3.getMessage();
										}

										int countSum_tDBOutput_3 = 0;
										for (int countEach_tDBOutput_3 : e_tDBOutput_3.getUpdateCounts()) {
											countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0
													: countEach_tDBOutput_3);
										}
										rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;

										insertedCount_tDBOutput_3 += countSum_tDBOutput_3;

										System.err.println(errormessage_tDBOutput_3);

									}
								}

								tos_count_tDBOutput_3++;

								/**
								 * [tDBOutput_3 main ] stop
								 */

								/**
								 * [tDBOutput_3 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_3";

								/**
								 * [tDBOutput_3 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_3 process_data_end ] start
								 */

								currentComponent = "tDBOutput_3";

								/**
								 * [tDBOutput_3 process_data_end ] stop
								 */

							} // End of branch "copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2"

							/**
							 * [tMap_3 process_data_end ] start
							 */

							currentComponent = "tMap_3";

							/**
							 * [tMap_3 process_data_end ] stop
							 */

						} // End of branch "row42"

						/**
						 * [tFileInputDelimited_3 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_3 end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

					}
				} finally {
					if (!((Object) ("C:/DEV/Data/Base Montauban/glossaire.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_3 != null) {
							fid_tFileInputDelimited_3.close();
						}
					}
					if (fid_tFileInputDelimited_3 != null) {
						globalMap.put("tFileInputDelimited_3_NB_LINE", fid_tFileInputDelimited_3.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_3", true);
				end_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_3 end ] stop
				 */

				/**
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row42");
				}

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
				 */

				/**
				 * [tDBOutput_3 end ] start
				 */

				currentComponent = "tDBOutput_3";

				try {
					int countSum_tDBOutput_3 = 0;
					if (pstmt_tDBOutput_3 != null && batchSizeCounter_tDBOutput_3 > 0) {

						for (int countEach_tDBOutput_3 : pstmt_tDBOutput_3.executeBatch()) {
							countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0 : countEach_tDBOutput_3);
						}
						rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;

					}

					insertedCount_tDBOutput_3 += countSum_tDBOutput_3;

				} catch (java.sql.BatchUpdateException e_tDBOutput_3) {
					globalMap.put("tDBOutput_3_ERROR_MESSAGE", e_tDBOutput_3.getMessage());
					java.sql.SQLException ne_tDBOutput_3 = e_tDBOutput_3.getNextException(), sqle_tDBOutput_3 = null;
					String errormessage_tDBOutput_3;
					if (ne_tDBOutput_3 != null) {
						// build new exception to provide the original cause
						sqle_tDBOutput_3 = new java.sql.SQLException(
								e_tDBOutput_3.getMessage() + "\ncaused by: " + ne_tDBOutput_3.getMessage(),
								ne_tDBOutput_3.getSQLState(), ne_tDBOutput_3.getErrorCode(), ne_tDBOutput_3);
						errormessage_tDBOutput_3 = sqle_tDBOutput_3.getMessage();
					} else {
						errormessage_tDBOutput_3 = e_tDBOutput_3.getMessage();
					}

					int countSum_tDBOutput_3 = 0;
					for (int countEach_tDBOutput_3 : e_tDBOutput_3.getUpdateCounts()) {
						countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0 : countEach_tDBOutput_3);
					}
					rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;

					insertedCount_tDBOutput_3 += countSum_tDBOutput_3;

					System.err.println(errormessage_tDBOutput_3);

				}

				if (pstmt_tDBOutput_3 != null) {

					pstmt_tDBOutput_3.close();
					resourceMap.remove("pstmt_tDBOutput_3");
				}
				resourceMap.put("statementClosed_tDBOutput_3", true);

				nb_line_deleted_tDBOutput_3 = nb_line_deleted_tDBOutput_3 + deletedCount_tDBOutput_3;
				nb_line_update_tDBOutput_3 = nb_line_update_tDBOutput_3 + updatedCount_tDBOutput_3;
				nb_line_inserted_tDBOutput_3 = nb_line_inserted_tDBOutput_3 + insertedCount_tDBOutput_3;
				nb_line_rejected_tDBOutput_3 = nb_line_rejected_tDBOutput_3 + rejectedCount_tDBOutput_3;

				globalMap.put("tDBOutput_3_NB_LINE", nb_line_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_UPDATED", nb_line_update_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_DELETED", nb_line_deleted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_3);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2");
				}

				ok_Hash.put("tDBOutput_3", true);
				end_Hash.put("tDBOutput_3", System.currentTimeMillis());

				/**
				 * [tDBOutput_3 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_3:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk7", 0, "ok");
			}

			tFileInputDelimited_4Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_3 finally ] start
				 */

				currentComponent = "tFileInputDelimited_3";

				/**
				 * [tFileInputDelimited_3 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tDBOutput_3 finally ] start
				 */

				currentComponent = "tDBOutput_3";

				if (resourceMap.get("statementClosed_tDBOutput_3") == null) {
					java.sql.PreparedStatement pstmtToClose_tDBOutput_3 = null;
					if ((pstmtToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_3")) != null) {
						pstmtToClose_tDBOutput_3.close();
					}
				}

				/**
				 * [tDBOutput_3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 1);
	}

	public static class copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct
			implements routines.system.IPersistableRow<copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int idpromotion;

		public int getIdpromotion() {
			return this.idpromotion;
		}

		public String date_debut_promotion;

		public String getDate_debut_promotion() {
			return this.date_debut_promotion;
		}

		public String date_fin_promotion;

		public String getDate_fin_promotion() {
			return this.date_fin_promotion;
		}

		public Float prix_promotion;

		public Float getPrix_promotion() {
			return this.prix_promotion;
		}

		public Float promotion_pourcentage;

		public Float getPromotion_pourcentage() {
			return this.promotion_pourcentage;
		}

		public Float prix_achat;

		public Float getPrix_achat() {
			return this.prix_achat;
		}

		public String idarticle;

		public String getIdarticle() {
			return this.idarticle;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.idpromotion;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct other = (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct) obj;

			if (this.idpromotion != other.idpromotion)
				return false;

			return true;
		}

		public void copyDataTo(copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct other) {

			other.idpromotion = this.idpromotion;
			other.date_debut_promotion = this.date_debut_promotion;
			other.date_fin_promotion = this.date_fin_promotion;
			other.prix_promotion = this.prix_promotion;
			other.promotion_pourcentage = this.promotion_pourcentage;
			other.prix_achat = this.prix_achat;
			other.idarticle = this.idarticle;

		}

		public void copyKeysDataTo(copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct other) {

			other.idpromotion = this.idpromotion;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idpromotion = dis.readInt();

					this.date_debut_promotion = readString(dis);

					this.date_fin_promotion = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.prix_promotion = null;
					} else {
						this.prix_promotion = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.promotion_pourcentage = null;
					} else {
						this.promotion_pourcentage = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.prix_achat = null;
					} else {
						this.prix_achat = dis.readFloat();
					}

					this.idarticle = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idpromotion = dis.readInt();

					this.date_debut_promotion = readString(dis);

					this.date_fin_promotion = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.prix_promotion = null;
					} else {
						this.prix_promotion = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.promotion_pourcentage = null;
					} else {
						this.promotion_pourcentage = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.prix_achat = null;
					} else {
						this.prix_achat = dis.readFloat();
					}

					this.idarticle = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.idpromotion);

				// String

				writeString(this.date_debut_promotion, dos);

				// String

				writeString(this.date_fin_promotion, dos);

				// Float

				if (this.prix_promotion == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prix_promotion);
				}

				// Float

				if (this.promotion_pourcentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.promotion_pourcentage);
				}

				// Float

				if (this.prix_achat == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prix_achat);
				}

				// String

				writeString(this.idarticle, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.idpromotion);

				// String

				writeString(this.date_debut_promotion, dos);

				// String

				writeString(this.date_fin_promotion, dos);

				// Float

				if (this.prix_promotion == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prix_promotion);
				}

				// Float

				if (this.promotion_pourcentage == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.promotion_pourcentage);
				}

				// Float

				if (this.prix_achat == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prix_achat);
				}

				// String

				writeString(this.idarticle, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idpromotion=" + String.valueOf(idpromotion));
			sb.append(",date_debut_promotion=" + date_debut_promotion);
			sb.append(",date_fin_promotion=" + date_fin_promotion);
			sb.append(",prix_promotion=" + String.valueOf(prix_promotion));
			sb.append(",promotion_pourcentage=" + String.valueOf(promotion_pourcentage));
			sb.append(",prix_achat=" + String.valueOf(prix_achat));
			sb.append(",idarticle=" + idarticle);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idpromotion, other.idpromotion);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row43Struct implements routines.system.IPersistableRow<row43Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];

		public String PREF;

		public String getPREF() {
			return this.PREF;
		}

		public String PDESIG1;

		public String getPDESIG1() {
			return this.PDESIG1;
		}

		public String PDESIG2;

		public String getPDESIG2() {
			return this.PDESIG2;
		}

		public String PDESIG3;

		public String getPDESIG3() {
			return this.PDESIG3;
		}

		public String PPRIX;

		public String getPPRIX() {
			return this.PPRIX;
		}

		public String PCTVA;

		public String getPCTVA() {
			return this.PCTVA;
		}

		public String PCTYV;

		public String getPCTYV() {
			return this.PCTYV;
		}

		public String PCPN;

		public String getPCPN() {
			return this.PCPN;
		}

		public String PPA;

		public String getPPA() {
			return this.PPA;
		}

		public String PQTE;

		public String getPQTE() {
			return this.PQTE;
		}

		public String PFORM01;

		public String getPFORM01() {
			return this.PFORM01;
		}

		public String PFORM02;

		public String getPFORM02() {
			return this.PFORM02;
		}

		public String PNBCAR;

		public String getPNBCAR() {
			return this.PNBCAR;
		}

		public String PVERT;

		public String getPVERT() {
			return this.PVERT;
		}

		public String PEMP2;

		public String getPEMP2() {
			return this.PEMP2;
		}

		public String PLIEU2;

		public String getPLIEU2() {
			return this.PLIEU2;
		}

		public String PREMIMAX;

		public String getPREMIMAX() {
			return this.PREMIMAX;
		}

		public String FILLER2;

		public String getFILLER2() {
			return this.FILLER2;
		}

		public String PMOUV;

		public String getPMOUV() {
			return this.PMOUV;
		}

		public String PTENU;

		public String getPTENU() {
			return this.PTENU;
		}

		public String PINCRE;

		public String getPINCRE() {
			return this.PINCRE;
		}

		public String PPROMPA;

		public String getPPROMPA() {
			return this.PPROMPA;
		}

		public String PACHAT;

		public String getPACHAT() {
			return this.PACHAT;
		}

		public String PARRCDT1;

		public String getPARRCDT1() {
			return this.PARRCDT1;
		}

		public String PARRCDT2;

		public String getPARRCDT2() {
			return this.PARRCDT2;
		}

		public String PECOTAXE;

		public String getPECOTAXE() {
			return this.PECOTAXE;
		}

		public String PPAQUET;

		public String getPPAQUET() {
			return this.PPAQUET;
		}

		public String PEMBAL;

		public String getPEMBAL() {
			return this.PEMBAL;
		}

		public String PGRAMA;

		public String getPGRAMA() {
			return this.PGRAMA;
		}

		public String PDATCRE;

		public String getPDATCRE() {
			return this.PDATCRE;
		}

		public String PDATPRIX;

		public String getPDATPRIX() {
			return this.PDATPRIX;
		}

		public String PDATPA1;

		public String getPDATPA1() {
			return this.PDATPA1;
		}

		public String PPA1;

		public String getPPA1() {
			return this.PPA1;
		}

		public String PPV1;

		public String getPPV1() {
			return this.PPV1;
		}

		public String PPRIXR;

		public String getPPRIXR() {
			return this.PPRIXR;
		}

		public String PPRIXRV1;

		public String getPPRIXRV1() {
			return this.PPRIXRV1;
		}

		public String PPRIXRV2;

		public String getPPRIXRV2() {
			return this.PPRIXRV2;
		}

		public String PPRIXRV3;

		public String getPPRIXRV3() {
			return this.PPRIXRV3;
		}

		public String PPRIREV1;

		public String getPPRIREV1() {
			return this.PPRIREV1;
		}

		public String PPRIREV2;

		public String getPPRIREV2() {
			return this.PPRIREV2;
		}

		public String PPRIREV3;

		public String getPPRIREV3() {
			return this.PPRIREV3;
		}

		public String PDATPV1;

		public String getPDATPV1() {
			return this.PDATPV1;
		}

		public String PMPA;

		public String getPMPA() {
			return this.PMPA;
		}

		public String PETIQ;

		public String getPETIQ() {
			return this.PETIQ;
		}

		public String PFOUR;

		public String getPFOUR() {
			return this.PFOUR;
		}

		public String PPRITTC;

		public String getPPRITTC() {
			return this.PPRITTC;
		}

		public String PIMOD;

		public String getPIMOD() {
			return this.PIMOD;
		}

		public String PPROMREM;

		public String getPPROMREM() {
			return this.PPROMREM;
		}

		public String PUV;

		public String getPUV() {
			return this.PUV;
		}

		public String PUA;

		public String getPUA() {
			return this.PUA;
		}

		public String PDELAI;

		public String getPDELAI() {
			return this.PDELAI;
		}

		public String PMINI;

		public String getPMINI() {
			return this.PMINI;
		}

		public String PMAXI;

		public String getPMAXI() {
			return this.PMAXI;
		}

		public String PCDE;

		public String getPCDE() {
			return this.PCDE;
		}

		public String PEMP;

		public String getPEMP() {
			return this.PEMP;
		}

		public String PPRI1;

		public String getPPRI1() {
			return this.PPRI1;
		}

		public String PPRI2;

		public String getPPRI2() {
			return this.PPRI2;
		}

		public String PPRI3;

		public String getPPRI3() {
			return this.PPRI3;
		}

		public String PPRI4;

		public String getPPRI4() {
			return this.PPRI4;
		}

		public String PPRI5;

		public String getPPRI5() {
			return this.PPRI5;
		}

		public String PPRI6;

		public String getPPRI6() {
			return this.PPRI6;
		}

		public String PREMI1;

		public String getPREMI1() {
			return this.PREMI1;
		}

		public String PREMI2;

		public String getPREMI2() {
			return this.PREMI2;
		}

		public String PREMI3;

		public String getPREMI3() {
			return this.PREMI3;
		}

		public String PREMI4;

		public String getPREMI4() {
			return this.PREMI4;
		}

		public String PREMI5;

		public String getPREMI5() {
			return this.PREMI5;
		}

		public String PREMI6;

		public String getPREMI6() {
			return this.PREMI6;
		}

		public String PQUANT1;

		public String getPQUANT1() {
			return this.PQUANT1;
		}

		public String PQUANT2;

		public String getPQUANT2() {
			return this.PQUANT2;
		}

		public String PQUANT3;

		public String getPQUANT3() {
			return this.PQUANT3;
		}

		public String PQUANT4;

		public String getPQUANT4() {
			return this.PQUANT4;
		}

		public String PQUANT5;

		public String getPQUANT5() {
			return this.PQUANT5;
		}

		public String PQUANT6;

		public String getPQUANT6() {
			return this.PQUANT6;
		}

		public String PEDAT;

		public String getPEDAT() {
			return this.PEDAT;
		}

		public String PSDAT;

		public String getPSDAT() {
			return this.PSDAT;
		}

		public String PRESV;

		public String getPRESV() {
			return this.PRESV;
		}

		public String PREFOU;

		public String getPREFOU() {
			return this.PREFOU;
		}

		public String PGAMEC;

		public String getPGAMEC() {
			return this.PGAMEC;
		}

		public String PGAMER;

		public String getPGAMER() {
			return this.PGAMER;
		}

		public String PGAMEN;

		public String getPGAMEN() {
			return this.PGAMEN;
		}

		public String PTPF;

		public String getPTPF() {
			return this.PTPF;
		}

		public String PSOMMEIL;

		public String getPSOMMEIL() {
			return this.PSOMMEIL;
		}

		public String PINV;

		public String getPINV() {
			return this.PINV;
		}

		public String PMAC;

		public String getPMAC() {
			return this.PMAC;
		}

		public String PREJ;

		public String getPREJ() {
			return this.PREJ;
		}

		public String PMOI;

		public String getPMOI() {
			return this.PMOI;
		}

		public String P1;

		public String getP1() {
			return this.P1;
		}

		public String P2;

		public String getP2() {
			return this.P2;
		}

		public String P3;

		public String getP3() {
			return this.P3;
		}

		public String P4;

		public String getP4() {
			return this.P4;
		}

		public String P5;

		public String getP5() {
			return this.P5;
		}

		public String P6;

		public String getP6() {
			return this.P6;
		}

		public String P7;

		public String getP7() {
			return this.P7;
		}

		public String P8;

		public String getP8() {
			return this.P8;
		}

		public String P9;

		public String getP9() {
			return this.P9;
		}

		public String P10;

		public String getP10() {
			return this.P10;
		}

		public String P11;

		public String getP11() {
			return this.P11;
		}

		public String P12;

		public String getP12() {
			return this.P12;
		}

		public String PAN;

		public String getPAN() {
			return this.PAN;
		}

		public String PAN1;

		public String getPAN1() {
			return this.PAN1;
		}

		public String PAN2;

		public String getPAN2() {
			return this.PAN2;
		}

		public String PPROM;

		public String getPPROM() {
			return this.PPROM;
		}

		public String PPROMD;

		public String getPPROMD() {
			return this.PPROMD;
		}

		public String PCONDI;

		public String getPCONDI() {
			return this.PCONDI;
		}

		public String PRESTE;

		public String getPRESTE() {
			return this.PRESTE;
		}

		public String PTITR;

		public String getPTITR() {
			return this.PTITR;
		}

		public String PPARAG;

		public String getPPARAG() {
			return this.PPARAG;
		}

		public String PPOID;

		public String getPPOID() {
			return this.PPOID;
		}

		public String PKLE;

		public String getPKLE() {
			return this.PKLE;
		}

		public String PLIEN;

		public String getPLIEN() {
			return this.PLIEN;
		}

		public String PSFAM;

		public String getPSFAM() {
			return this.PSFAM;
		}

		public String PREF01;

		public String getPREF01() {
			return this.PREF01;
		}

		public String PREF02;

		public String getPREF02() {
			return this.PREF02;
		}

		public String PREF03;

		public String getPREF03() {
			return this.PREF03;
		}

		public String PTYPE;

		public String getPTYPE() {
			return this.PTYPE;
		}

		public String PLIEU;

		public String getPLIEU() {
			return this.PLIEU;
		}

		public String PCATAL;

		public String getPCATAL() {
			return this.PCATAL;
		}

		public String PUV01;

		public String getPUV01() {
			return this.PUV01;
		}

		public String PCONDI01;

		public String getPCONDI01() {
			return this.PCONDI01;
		}

		public String PUV02;

		public String getPUV02() {
			return this.PUV02;
		}

		public String PCONDI02;

		public String getPCONDI02() {
			return this.PCONDI02;
		}

		public String PPROMDEB;

		public String getPPROMDEB() {
			return this.PPROMDEB;
		}

		public String PREMFOU;

		public String getPREMFOU() {
			return this.PREMFOU;
		}

		public String PQTE01;

		public String getPQTE01() {
			return this.PQTE01;
		}

		public String PQTE02;

		public String getPQTE02() {
			return this.PQTE02;
		}

		public String PRX01;

		public String getPRX01() {
			return this.PRX01;
		}

		public String PRX02;

		public String getPRX02() {
			return this.PRX02;
		}

		public String PCUBAG;

		public String getPCUBAG() {
			return this.PCUBAG;
		}

		public String PREVPOI;

		public String getPREVPOI() {
			return this.PREVPOI;
		}

		public String PREVCUB;

		public String getPREVCUB() {
			return this.PREVCUB;
		}

		public String PREVFAP;

		public String getPREVFAP() {
			return this.PREVFAP;
		}

		public String PINTERNET;

		public String getPINTERNET() {
			return this.PINTERNET;
		}

		public String PFIDEL;

		public String getPFIDEL() {
			return this.PFIDEL;
		}

		public String PCOMMANDE;

		public String getPCOMMANDE() {
			return this.PCOMMANDE;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.PREF = readString(dis);

					this.PDESIG1 = readString(dis);

					this.PDESIG2 = readString(dis);

					this.PDESIG3 = readString(dis);

					this.PPRIX = readString(dis);

					this.PCTVA = readString(dis);

					this.PCTYV = readString(dis);

					this.PCPN = readString(dis);

					this.PPA = readString(dis);

					this.PQTE = readString(dis);

					this.PFORM01 = readString(dis);

					this.PFORM02 = readString(dis);

					this.PNBCAR = readString(dis);

					this.PVERT = readString(dis);

					this.PEMP2 = readString(dis);

					this.PLIEU2 = readString(dis);

					this.PREMIMAX = readString(dis);

					this.FILLER2 = readString(dis);

					this.PMOUV = readString(dis);

					this.PTENU = readString(dis);

					this.PINCRE = readString(dis);

					this.PPROMPA = readString(dis);

					this.PACHAT = readString(dis);

					this.PARRCDT1 = readString(dis);

					this.PARRCDT2 = readString(dis);

					this.PECOTAXE = readString(dis);

					this.PPAQUET = readString(dis);

					this.PEMBAL = readString(dis);

					this.PGRAMA = readString(dis);

					this.PDATCRE = readString(dis);

					this.PDATPRIX = readString(dis);

					this.PDATPA1 = readString(dis);

					this.PPA1 = readString(dis);

					this.PPV1 = readString(dis);

					this.PPRIXR = readString(dis);

					this.PPRIXRV1 = readString(dis);

					this.PPRIXRV2 = readString(dis);

					this.PPRIXRV3 = readString(dis);

					this.PPRIREV1 = readString(dis);

					this.PPRIREV2 = readString(dis);

					this.PPRIREV3 = readString(dis);

					this.PDATPV1 = readString(dis);

					this.PMPA = readString(dis);

					this.PETIQ = readString(dis);

					this.PFOUR = readString(dis);

					this.PPRITTC = readString(dis);

					this.PIMOD = readString(dis);

					this.PPROMREM = readString(dis);

					this.PUV = readString(dis);

					this.PUA = readString(dis);

					this.PDELAI = readString(dis);

					this.PMINI = readString(dis);

					this.PMAXI = readString(dis);

					this.PCDE = readString(dis);

					this.PEMP = readString(dis);

					this.PPRI1 = readString(dis);

					this.PPRI2 = readString(dis);

					this.PPRI3 = readString(dis);

					this.PPRI4 = readString(dis);

					this.PPRI5 = readString(dis);

					this.PPRI6 = readString(dis);

					this.PREMI1 = readString(dis);

					this.PREMI2 = readString(dis);

					this.PREMI3 = readString(dis);

					this.PREMI4 = readString(dis);

					this.PREMI5 = readString(dis);

					this.PREMI6 = readString(dis);

					this.PQUANT1 = readString(dis);

					this.PQUANT2 = readString(dis);

					this.PQUANT3 = readString(dis);

					this.PQUANT4 = readString(dis);

					this.PQUANT5 = readString(dis);

					this.PQUANT6 = readString(dis);

					this.PEDAT = readString(dis);

					this.PSDAT = readString(dis);

					this.PRESV = readString(dis);

					this.PREFOU = readString(dis);

					this.PGAMEC = readString(dis);

					this.PGAMER = readString(dis);

					this.PGAMEN = readString(dis);

					this.PTPF = readString(dis);

					this.PSOMMEIL = readString(dis);

					this.PINV = readString(dis);

					this.PMAC = readString(dis);

					this.PREJ = readString(dis);

					this.PMOI = readString(dis);

					this.P1 = readString(dis);

					this.P2 = readString(dis);

					this.P3 = readString(dis);

					this.P4 = readString(dis);

					this.P5 = readString(dis);

					this.P6 = readString(dis);

					this.P7 = readString(dis);

					this.P8 = readString(dis);

					this.P9 = readString(dis);

					this.P10 = readString(dis);

					this.P11 = readString(dis);

					this.P12 = readString(dis);

					this.PAN = readString(dis);

					this.PAN1 = readString(dis);

					this.PAN2 = readString(dis);

					this.PPROM = readString(dis);

					this.PPROMD = readString(dis);

					this.PCONDI = readString(dis);

					this.PRESTE = readString(dis);

					this.PTITR = readString(dis);

					this.PPARAG = readString(dis);

					this.PPOID = readString(dis);

					this.PKLE = readString(dis);

					this.PLIEN = readString(dis);

					this.PSFAM = readString(dis);

					this.PREF01 = readString(dis);

					this.PREF02 = readString(dis);

					this.PREF03 = readString(dis);

					this.PTYPE = readString(dis);

					this.PLIEU = readString(dis);

					this.PCATAL = readString(dis);

					this.PUV01 = readString(dis);

					this.PCONDI01 = readString(dis);

					this.PUV02 = readString(dis);

					this.PCONDI02 = readString(dis);

					this.PPROMDEB = readString(dis);

					this.PREMFOU = readString(dis);

					this.PQTE01 = readString(dis);

					this.PQTE02 = readString(dis);

					this.PRX01 = readString(dis);

					this.PRX02 = readString(dis);

					this.PCUBAG = readString(dis);

					this.PREVPOI = readString(dis);

					this.PREVCUB = readString(dis);

					this.PREVFAP = readString(dis);

					this.PINTERNET = readString(dis);

					this.PFIDEL = readString(dis);

					this.PCOMMANDE = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.PREF = readString(dis);

					this.PDESIG1 = readString(dis);

					this.PDESIG2 = readString(dis);

					this.PDESIG3 = readString(dis);

					this.PPRIX = readString(dis);

					this.PCTVA = readString(dis);

					this.PCTYV = readString(dis);

					this.PCPN = readString(dis);

					this.PPA = readString(dis);

					this.PQTE = readString(dis);

					this.PFORM01 = readString(dis);

					this.PFORM02 = readString(dis);

					this.PNBCAR = readString(dis);

					this.PVERT = readString(dis);

					this.PEMP2 = readString(dis);

					this.PLIEU2 = readString(dis);

					this.PREMIMAX = readString(dis);

					this.FILLER2 = readString(dis);

					this.PMOUV = readString(dis);

					this.PTENU = readString(dis);

					this.PINCRE = readString(dis);

					this.PPROMPA = readString(dis);

					this.PACHAT = readString(dis);

					this.PARRCDT1 = readString(dis);

					this.PARRCDT2 = readString(dis);

					this.PECOTAXE = readString(dis);

					this.PPAQUET = readString(dis);

					this.PEMBAL = readString(dis);

					this.PGRAMA = readString(dis);

					this.PDATCRE = readString(dis);

					this.PDATPRIX = readString(dis);

					this.PDATPA1 = readString(dis);

					this.PPA1 = readString(dis);

					this.PPV1 = readString(dis);

					this.PPRIXR = readString(dis);

					this.PPRIXRV1 = readString(dis);

					this.PPRIXRV2 = readString(dis);

					this.PPRIXRV3 = readString(dis);

					this.PPRIREV1 = readString(dis);

					this.PPRIREV2 = readString(dis);

					this.PPRIREV3 = readString(dis);

					this.PDATPV1 = readString(dis);

					this.PMPA = readString(dis);

					this.PETIQ = readString(dis);

					this.PFOUR = readString(dis);

					this.PPRITTC = readString(dis);

					this.PIMOD = readString(dis);

					this.PPROMREM = readString(dis);

					this.PUV = readString(dis);

					this.PUA = readString(dis);

					this.PDELAI = readString(dis);

					this.PMINI = readString(dis);

					this.PMAXI = readString(dis);

					this.PCDE = readString(dis);

					this.PEMP = readString(dis);

					this.PPRI1 = readString(dis);

					this.PPRI2 = readString(dis);

					this.PPRI3 = readString(dis);

					this.PPRI4 = readString(dis);

					this.PPRI5 = readString(dis);

					this.PPRI6 = readString(dis);

					this.PREMI1 = readString(dis);

					this.PREMI2 = readString(dis);

					this.PREMI3 = readString(dis);

					this.PREMI4 = readString(dis);

					this.PREMI5 = readString(dis);

					this.PREMI6 = readString(dis);

					this.PQUANT1 = readString(dis);

					this.PQUANT2 = readString(dis);

					this.PQUANT3 = readString(dis);

					this.PQUANT4 = readString(dis);

					this.PQUANT5 = readString(dis);

					this.PQUANT6 = readString(dis);

					this.PEDAT = readString(dis);

					this.PSDAT = readString(dis);

					this.PRESV = readString(dis);

					this.PREFOU = readString(dis);

					this.PGAMEC = readString(dis);

					this.PGAMER = readString(dis);

					this.PGAMEN = readString(dis);

					this.PTPF = readString(dis);

					this.PSOMMEIL = readString(dis);

					this.PINV = readString(dis);

					this.PMAC = readString(dis);

					this.PREJ = readString(dis);

					this.PMOI = readString(dis);

					this.P1 = readString(dis);

					this.P2 = readString(dis);

					this.P3 = readString(dis);

					this.P4 = readString(dis);

					this.P5 = readString(dis);

					this.P6 = readString(dis);

					this.P7 = readString(dis);

					this.P8 = readString(dis);

					this.P9 = readString(dis);

					this.P10 = readString(dis);

					this.P11 = readString(dis);

					this.P12 = readString(dis);

					this.PAN = readString(dis);

					this.PAN1 = readString(dis);

					this.PAN2 = readString(dis);

					this.PPROM = readString(dis);

					this.PPROMD = readString(dis);

					this.PCONDI = readString(dis);

					this.PRESTE = readString(dis);

					this.PTITR = readString(dis);

					this.PPARAG = readString(dis);

					this.PPOID = readString(dis);

					this.PKLE = readString(dis);

					this.PLIEN = readString(dis);

					this.PSFAM = readString(dis);

					this.PREF01 = readString(dis);

					this.PREF02 = readString(dis);

					this.PREF03 = readString(dis);

					this.PTYPE = readString(dis);

					this.PLIEU = readString(dis);

					this.PCATAL = readString(dis);

					this.PUV01 = readString(dis);

					this.PCONDI01 = readString(dis);

					this.PUV02 = readString(dis);

					this.PCONDI02 = readString(dis);

					this.PPROMDEB = readString(dis);

					this.PREMFOU = readString(dis);

					this.PQTE01 = readString(dis);

					this.PQTE02 = readString(dis);

					this.PRX01 = readString(dis);

					this.PRX02 = readString(dis);

					this.PCUBAG = readString(dis);

					this.PREVPOI = readString(dis);

					this.PREVCUB = readString(dis);

					this.PREVFAP = readString(dis);

					this.PINTERNET = readString(dis);

					this.PFIDEL = readString(dis);

					this.PCOMMANDE = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PREF, dos);

				// String

				writeString(this.PDESIG1, dos);

				// String

				writeString(this.PDESIG2, dos);

				// String

				writeString(this.PDESIG3, dos);

				// String

				writeString(this.PPRIX, dos);

				// String

				writeString(this.PCTVA, dos);

				// String

				writeString(this.PCTYV, dos);

				// String

				writeString(this.PCPN, dos);

				// String

				writeString(this.PPA, dos);

				// String

				writeString(this.PQTE, dos);

				// String

				writeString(this.PFORM01, dos);

				// String

				writeString(this.PFORM02, dos);

				// String

				writeString(this.PNBCAR, dos);

				// String

				writeString(this.PVERT, dos);

				// String

				writeString(this.PEMP2, dos);

				// String

				writeString(this.PLIEU2, dos);

				// String

				writeString(this.PREMIMAX, dos);

				// String

				writeString(this.FILLER2, dos);

				// String

				writeString(this.PMOUV, dos);

				// String

				writeString(this.PTENU, dos);

				// String

				writeString(this.PINCRE, dos);

				// String

				writeString(this.PPROMPA, dos);

				// String

				writeString(this.PACHAT, dos);

				// String

				writeString(this.PARRCDT1, dos);

				// String

				writeString(this.PARRCDT2, dos);

				// String

				writeString(this.PECOTAXE, dos);

				// String

				writeString(this.PPAQUET, dos);

				// String

				writeString(this.PEMBAL, dos);

				// String

				writeString(this.PGRAMA, dos);

				// String

				writeString(this.PDATCRE, dos);

				// String

				writeString(this.PDATPRIX, dos);

				// String

				writeString(this.PDATPA1, dos);

				// String

				writeString(this.PPA1, dos);

				// String

				writeString(this.PPV1, dos);

				// String

				writeString(this.PPRIXR, dos);

				// String

				writeString(this.PPRIXRV1, dos);

				// String

				writeString(this.PPRIXRV2, dos);

				// String

				writeString(this.PPRIXRV3, dos);

				// String

				writeString(this.PPRIREV1, dos);

				// String

				writeString(this.PPRIREV2, dos);

				// String

				writeString(this.PPRIREV3, dos);

				// String

				writeString(this.PDATPV1, dos);

				// String

				writeString(this.PMPA, dos);

				// String

				writeString(this.PETIQ, dos);

				// String

				writeString(this.PFOUR, dos);

				// String

				writeString(this.PPRITTC, dos);

				// String

				writeString(this.PIMOD, dos);

				// String

				writeString(this.PPROMREM, dos);

				// String

				writeString(this.PUV, dos);

				// String

				writeString(this.PUA, dos);

				// String

				writeString(this.PDELAI, dos);

				// String

				writeString(this.PMINI, dos);

				// String

				writeString(this.PMAXI, dos);

				// String

				writeString(this.PCDE, dos);

				// String

				writeString(this.PEMP, dos);

				// String

				writeString(this.PPRI1, dos);

				// String

				writeString(this.PPRI2, dos);

				// String

				writeString(this.PPRI3, dos);

				// String

				writeString(this.PPRI4, dos);

				// String

				writeString(this.PPRI5, dos);

				// String

				writeString(this.PPRI6, dos);

				// String

				writeString(this.PREMI1, dos);

				// String

				writeString(this.PREMI2, dos);

				// String

				writeString(this.PREMI3, dos);

				// String

				writeString(this.PREMI4, dos);

				// String

				writeString(this.PREMI5, dos);

				// String

				writeString(this.PREMI6, dos);

				// String

				writeString(this.PQUANT1, dos);

				// String

				writeString(this.PQUANT2, dos);

				// String

				writeString(this.PQUANT3, dos);

				// String

				writeString(this.PQUANT4, dos);

				// String

				writeString(this.PQUANT5, dos);

				// String

				writeString(this.PQUANT6, dos);

				// String

				writeString(this.PEDAT, dos);

				// String

				writeString(this.PSDAT, dos);

				// String

				writeString(this.PRESV, dos);

				// String

				writeString(this.PREFOU, dos);

				// String

				writeString(this.PGAMEC, dos);

				// String

				writeString(this.PGAMER, dos);

				// String

				writeString(this.PGAMEN, dos);

				// String

				writeString(this.PTPF, dos);

				// String

				writeString(this.PSOMMEIL, dos);

				// String

				writeString(this.PINV, dos);

				// String

				writeString(this.PMAC, dos);

				// String

				writeString(this.PREJ, dos);

				// String

				writeString(this.PMOI, dos);

				// String

				writeString(this.P1, dos);

				// String

				writeString(this.P2, dos);

				// String

				writeString(this.P3, dos);

				// String

				writeString(this.P4, dos);

				// String

				writeString(this.P5, dos);

				// String

				writeString(this.P6, dos);

				// String

				writeString(this.P7, dos);

				// String

				writeString(this.P8, dos);

				// String

				writeString(this.P9, dos);

				// String

				writeString(this.P10, dos);

				// String

				writeString(this.P11, dos);

				// String

				writeString(this.P12, dos);

				// String

				writeString(this.PAN, dos);

				// String

				writeString(this.PAN1, dos);

				// String

				writeString(this.PAN2, dos);

				// String

				writeString(this.PPROM, dos);

				// String

				writeString(this.PPROMD, dos);

				// String

				writeString(this.PCONDI, dos);

				// String

				writeString(this.PRESTE, dos);

				// String

				writeString(this.PTITR, dos);

				// String

				writeString(this.PPARAG, dos);

				// String

				writeString(this.PPOID, dos);

				// String

				writeString(this.PKLE, dos);

				// String

				writeString(this.PLIEN, dos);

				// String

				writeString(this.PSFAM, dos);

				// String

				writeString(this.PREF01, dos);

				// String

				writeString(this.PREF02, dos);

				// String

				writeString(this.PREF03, dos);

				// String

				writeString(this.PTYPE, dos);

				// String

				writeString(this.PLIEU, dos);

				// String

				writeString(this.PCATAL, dos);

				// String

				writeString(this.PUV01, dos);

				// String

				writeString(this.PCONDI01, dos);

				// String

				writeString(this.PUV02, dos);

				// String

				writeString(this.PCONDI02, dos);

				// String

				writeString(this.PPROMDEB, dos);

				// String

				writeString(this.PREMFOU, dos);

				// String

				writeString(this.PQTE01, dos);

				// String

				writeString(this.PQTE02, dos);

				// String

				writeString(this.PRX01, dos);

				// String

				writeString(this.PRX02, dos);

				// String

				writeString(this.PCUBAG, dos);

				// String

				writeString(this.PREVPOI, dos);

				// String

				writeString(this.PREVCUB, dos);

				// String

				writeString(this.PREVFAP, dos);

				// String

				writeString(this.PINTERNET, dos);

				// String

				writeString(this.PFIDEL, dos);

				// String

				writeString(this.PCOMMANDE, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PREF, dos);

				// String

				writeString(this.PDESIG1, dos);

				// String

				writeString(this.PDESIG2, dos);

				// String

				writeString(this.PDESIG3, dos);

				// String

				writeString(this.PPRIX, dos);

				// String

				writeString(this.PCTVA, dos);

				// String

				writeString(this.PCTYV, dos);

				// String

				writeString(this.PCPN, dos);

				// String

				writeString(this.PPA, dos);

				// String

				writeString(this.PQTE, dos);

				// String

				writeString(this.PFORM01, dos);

				// String

				writeString(this.PFORM02, dos);

				// String

				writeString(this.PNBCAR, dos);

				// String

				writeString(this.PVERT, dos);

				// String

				writeString(this.PEMP2, dos);

				// String

				writeString(this.PLIEU2, dos);

				// String

				writeString(this.PREMIMAX, dos);

				// String

				writeString(this.FILLER2, dos);

				// String

				writeString(this.PMOUV, dos);

				// String

				writeString(this.PTENU, dos);

				// String

				writeString(this.PINCRE, dos);

				// String

				writeString(this.PPROMPA, dos);

				// String

				writeString(this.PACHAT, dos);

				// String

				writeString(this.PARRCDT1, dos);

				// String

				writeString(this.PARRCDT2, dos);

				// String

				writeString(this.PECOTAXE, dos);

				// String

				writeString(this.PPAQUET, dos);

				// String

				writeString(this.PEMBAL, dos);

				// String

				writeString(this.PGRAMA, dos);

				// String

				writeString(this.PDATCRE, dos);

				// String

				writeString(this.PDATPRIX, dos);

				// String

				writeString(this.PDATPA1, dos);

				// String

				writeString(this.PPA1, dos);

				// String

				writeString(this.PPV1, dos);

				// String

				writeString(this.PPRIXR, dos);

				// String

				writeString(this.PPRIXRV1, dos);

				// String

				writeString(this.PPRIXRV2, dos);

				// String

				writeString(this.PPRIXRV3, dos);

				// String

				writeString(this.PPRIREV1, dos);

				// String

				writeString(this.PPRIREV2, dos);

				// String

				writeString(this.PPRIREV3, dos);

				// String

				writeString(this.PDATPV1, dos);

				// String

				writeString(this.PMPA, dos);

				// String

				writeString(this.PETIQ, dos);

				// String

				writeString(this.PFOUR, dos);

				// String

				writeString(this.PPRITTC, dos);

				// String

				writeString(this.PIMOD, dos);

				// String

				writeString(this.PPROMREM, dos);

				// String

				writeString(this.PUV, dos);

				// String

				writeString(this.PUA, dos);

				// String

				writeString(this.PDELAI, dos);

				// String

				writeString(this.PMINI, dos);

				// String

				writeString(this.PMAXI, dos);

				// String

				writeString(this.PCDE, dos);

				// String

				writeString(this.PEMP, dos);

				// String

				writeString(this.PPRI1, dos);

				// String

				writeString(this.PPRI2, dos);

				// String

				writeString(this.PPRI3, dos);

				// String

				writeString(this.PPRI4, dos);

				// String

				writeString(this.PPRI5, dos);

				// String

				writeString(this.PPRI6, dos);

				// String

				writeString(this.PREMI1, dos);

				// String

				writeString(this.PREMI2, dos);

				// String

				writeString(this.PREMI3, dos);

				// String

				writeString(this.PREMI4, dos);

				// String

				writeString(this.PREMI5, dos);

				// String

				writeString(this.PREMI6, dos);

				// String

				writeString(this.PQUANT1, dos);

				// String

				writeString(this.PQUANT2, dos);

				// String

				writeString(this.PQUANT3, dos);

				// String

				writeString(this.PQUANT4, dos);

				// String

				writeString(this.PQUANT5, dos);

				// String

				writeString(this.PQUANT6, dos);

				// String

				writeString(this.PEDAT, dos);

				// String

				writeString(this.PSDAT, dos);

				// String

				writeString(this.PRESV, dos);

				// String

				writeString(this.PREFOU, dos);

				// String

				writeString(this.PGAMEC, dos);

				// String

				writeString(this.PGAMER, dos);

				// String

				writeString(this.PGAMEN, dos);

				// String

				writeString(this.PTPF, dos);

				// String

				writeString(this.PSOMMEIL, dos);

				// String

				writeString(this.PINV, dos);

				// String

				writeString(this.PMAC, dos);

				// String

				writeString(this.PREJ, dos);

				// String

				writeString(this.PMOI, dos);

				// String

				writeString(this.P1, dos);

				// String

				writeString(this.P2, dos);

				// String

				writeString(this.P3, dos);

				// String

				writeString(this.P4, dos);

				// String

				writeString(this.P5, dos);

				// String

				writeString(this.P6, dos);

				// String

				writeString(this.P7, dos);

				// String

				writeString(this.P8, dos);

				// String

				writeString(this.P9, dos);

				// String

				writeString(this.P10, dos);

				// String

				writeString(this.P11, dos);

				// String

				writeString(this.P12, dos);

				// String

				writeString(this.PAN, dos);

				// String

				writeString(this.PAN1, dos);

				// String

				writeString(this.PAN2, dos);

				// String

				writeString(this.PPROM, dos);

				// String

				writeString(this.PPROMD, dos);

				// String

				writeString(this.PCONDI, dos);

				// String

				writeString(this.PRESTE, dos);

				// String

				writeString(this.PTITR, dos);

				// String

				writeString(this.PPARAG, dos);

				// String

				writeString(this.PPOID, dos);

				// String

				writeString(this.PKLE, dos);

				// String

				writeString(this.PLIEN, dos);

				// String

				writeString(this.PSFAM, dos);

				// String

				writeString(this.PREF01, dos);

				// String

				writeString(this.PREF02, dos);

				// String

				writeString(this.PREF03, dos);

				// String

				writeString(this.PTYPE, dos);

				// String

				writeString(this.PLIEU, dos);

				// String

				writeString(this.PCATAL, dos);

				// String

				writeString(this.PUV01, dos);

				// String

				writeString(this.PCONDI01, dos);

				// String

				writeString(this.PUV02, dos);

				// String

				writeString(this.PCONDI02, dos);

				// String

				writeString(this.PPROMDEB, dos);

				// String

				writeString(this.PREMFOU, dos);

				// String

				writeString(this.PQTE01, dos);

				// String

				writeString(this.PQTE02, dos);

				// String

				writeString(this.PRX01, dos);

				// String

				writeString(this.PRX02, dos);

				// String

				writeString(this.PCUBAG, dos);

				// String

				writeString(this.PREVPOI, dos);

				// String

				writeString(this.PREVCUB, dos);

				// String

				writeString(this.PREVFAP, dos);

				// String

				writeString(this.PINTERNET, dos);

				// String

				writeString(this.PFIDEL, dos);

				// String

				writeString(this.PCOMMANDE, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PREF=" + PREF);
			sb.append(",PDESIG1=" + PDESIG1);
			sb.append(",PDESIG2=" + PDESIG2);
			sb.append(",PDESIG3=" + PDESIG3);
			sb.append(",PPRIX=" + PPRIX);
			sb.append(",PCTVA=" + PCTVA);
			sb.append(",PCTYV=" + PCTYV);
			sb.append(",PCPN=" + PCPN);
			sb.append(",PPA=" + PPA);
			sb.append(",PQTE=" + PQTE);
			sb.append(",PFORM01=" + PFORM01);
			sb.append(",PFORM02=" + PFORM02);
			sb.append(",PNBCAR=" + PNBCAR);
			sb.append(",PVERT=" + PVERT);
			sb.append(",PEMP2=" + PEMP2);
			sb.append(",PLIEU2=" + PLIEU2);
			sb.append(",PREMIMAX=" + PREMIMAX);
			sb.append(",FILLER2=" + FILLER2);
			sb.append(",PMOUV=" + PMOUV);
			sb.append(",PTENU=" + PTENU);
			sb.append(",PINCRE=" + PINCRE);
			sb.append(",PPROMPA=" + PPROMPA);
			sb.append(",PACHAT=" + PACHAT);
			sb.append(",PARRCDT1=" + PARRCDT1);
			sb.append(",PARRCDT2=" + PARRCDT2);
			sb.append(",PECOTAXE=" + PECOTAXE);
			sb.append(",PPAQUET=" + PPAQUET);
			sb.append(",PEMBAL=" + PEMBAL);
			sb.append(",PGRAMA=" + PGRAMA);
			sb.append(",PDATCRE=" + PDATCRE);
			sb.append(",PDATPRIX=" + PDATPRIX);
			sb.append(",PDATPA1=" + PDATPA1);
			sb.append(",PPA1=" + PPA1);
			sb.append(",PPV1=" + PPV1);
			sb.append(",PPRIXR=" + PPRIXR);
			sb.append(",PPRIXRV1=" + PPRIXRV1);
			sb.append(",PPRIXRV2=" + PPRIXRV2);
			sb.append(",PPRIXRV3=" + PPRIXRV3);
			sb.append(",PPRIREV1=" + PPRIREV1);
			sb.append(",PPRIREV2=" + PPRIREV2);
			sb.append(",PPRIREV3=" + PPRIREV3);
			sb.append(",PDATPV1=" + PDATPV1);
			sb.append(",PMPA=" + PMPA);
			sb.append(",PETIQ=" + PETIQ);
			sb.append(",PFOUR=" + PFOUR);
			sb.append(",PPRITTC=" + PPRITTC);
			sb.append(",PIMOD=" + PIMOD);
			sb.append(",PPROMREM=" + PPROMREM);
			sb.append(",PUV=" + PUV);
			sb.append(",PUA=" + PUA);
			sb.append(",PDELAI=" + PDELAI);
			sb.append(",PMINI=" + PMINI);
			sb.append(",PMAXI=" + PMAXI);
			sb.append(",PCDE=" + PCDE);
			sb.append(",PEMP=" + PEMP);
			sb.append(",PPRI1=" + PPRI1);
			sb.append(",PPRI2=" + PPRI2);
			sb.append(",PPRI3=" + PPRI3);
			sb.append(",PPRI4=" + PPRI4);
			sb.append(",PPRI5=" + PPRI5);
			sb.append(",PPRI6=" + PPRI6);
			sb.append(",PREMI1=" + PREMI1);
			sb.append(",PREMI2=" + PREMI2);
			sb.append(",PREMI3=" + PREMI3);
			sb.append(",PREMI4=" + PREMI4);
			sb.append(",PREMI5=" + PREMI5);
			sb.append(",PREMI6=" + PREMI6);
			sb.append(",PQUANT1=" + PQUANT1);
			sb.append(",PQUANT2=" + PQUANT2);
			sb.append(",PQUANT3=" + PQUANT3);
			sb.append(",PQUANT4=" + PQUANT4);
			sb.append(",PQUANT5=" + PQUANT5);
			sb.append(",PQUANT6=" + PQUANT6);
			sb.append(",PEDAT=" + PEDAT);
			sb.append(",PSDAT=" + PSDAT);
			sb.append(",PRESV=" + PRESV);
			sb.append(",PREFOU=" + PREFOU);
			sb.append(",PGAMEC=" + PGAMEC);
			sb.append(",PGAMER=" + PGAMER);
			sb.append(",PGAMEN=" + PGAMEN);
			sb.append(",PTPF=" + PTPF);
			sb.append(",PSOMMEIL=" + PSOMMEIL);
			sb.append(",PINV=" + PINV);
			sb.append(",PMAC=" + PMAC);
			sb.append(",PREJ=" + PREJ);
			sb.append(",PMOI=" + PMOI);
			sb.append(",P1=" + P1);
			sb.append(",P2=" + P2);
			sb.append(",P3=" + P3);
			sb.append(",P4=" + P4);
			sb.append(",P5=" + P5);
			sb.append(",P6=" + P6);
			sb.append(",P7=" + P7);
			sb.append(",P8=" + P8);
			sb.append(",P9=" + P9);
			sb.append(",P10=" + P10);
			sb.append(",P11=" + P11);
			sb.append(",P12=" + P12);
			sb.append(",PAN=" + PAN);
			sb.append(",PAN1=" + PAN1);
			sb.append(",PAN2=" + PAN2);
			sb.append(",PPROM=" + PPROM);
			sb.append(",PPROMD=" + PPROMD);
			sb.append(",PCONDI=" + PCONDI);
			sb.append(",PRESTE=" + PRESTE);
			sb.append(",PTITR=" + PTITR);
			sb.append(",PPARAG=" + PPARAG);
			sb.append(",PPOID=" + PPOID);
			sb.append(",PKLE=" + PKLE);
			sb.append(",PLIEN=" + PLIEN);
			sb.append(",PSFAM=" + PSFAM);
			sb.append(",PREF01=" + PREF01);
			sb.append(",PREF02=" + PREF02);
			sb.append(",PREF03=" + PREF03);
			sb.append(",PTYPE=" + PTYPE);
			sb.append(",PLIEU=" + PLIEU);
			sb.append(",PCATAL=" + PCATAL);
			sb.append(",PUV01=" + PUV01);
			sb.append(",PCONDI01=" + PCONDI01);
			sb.append(",PUV02=" + PUV02);
			sb.append(",PCONDI02=" + PCONDI02);
			sb.append(",PPROMDEB=" + PPROMDEB);
			sb.append(",PREMFOU=" + PREMFOU);
			sb.append(",PQTE01=" + PQTE01);
			sb.append(",PQTE02=" + PQTE02);
			sb.append(",PRX01=" + PRX01);
			sb.append(",PRX02=" + PRX02);
			sb.append(",PCUBAG=" + PCUBAG);
			sb.append(",PREVPOI=" + PREVPOI);
			sb.append(",PREVCUB=" + PREVCUB);
			sb.append(",PREVFAP=" + PREVFAP);
			sb.append(",PINTERNET=" + PINTERNET);
			sb.append(",PFIDEL=" + PFIDEL);
			sb.append(",PCOMMANDE=" + PCOMMANDE);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row43Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row43Struct row43 = new row43Struct();
				copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0 = new copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct();

				/**
				 * [tDBOutput_4 begin ] start
				 */

				ok_Hash.put("tDBOutput_4", false);
				start_Hash.put("tDBOutput_4", System.currentTimeMillis());

				currentComponent = "tDBOutput_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0,
							"copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0");
				}

				int tos_count_tDBOutput_4 = 0;

				String dbschema_tDBOutput_4 = null;
				dbschema_tDBOutput_4 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_4 = null;
				if (dbschema_tDBOutput_4 == null || dbschema_tDBOutput_4.trim().length() == 0) {
					tableName_tDBOutput_4 = ("promotion");
				} else {
					tableName_tDBOutput_4 = dbschema_tDBOutput_4 + "\".\"" + ("promotion");
				}

				int nb_line_tDBOutput_4 = 0;
				int nb_line_update_tDBOutput_4 = 0;
				int nb_line_inserted_tDBOutput_4 = 0;
				int nb_line_deleted_tDBOutput_4 = 0;
				int nb_line_rejected_tDBOutput_4 = 0;

				int deletedCount_tDBOutput_4 = 0;
				int updatedCount_tDBOutput_4 = 0;
				int insertedCount_tDBOutput_4 = 0;
				int rowsToCommitCount_tDBOutput_4 = 0;
				int rejectedCount_tDBOutput_4 = 0;

				boolean whetherReject_tDBOutput_4 = false;

				java.sql.Connection conn_tDBOutput_4 = null;
				String dbUser_tDBOutput_4 = null;

				conn_tDBOutput_4 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int batchSize_tDBOutput_4 = 10000;
				int batchSizeCounter_tDBOutput_4 = 0;

				int count_tDBOutput_4 = 0;
				try (java.sql.Statement stmtClear_tDBOutput_4 = conn_tDBOutput_4.createStatement()) {
					stmtClear_tDBOutput_4.executeUpdate("DELETE FROM \"" + tableName_tDBOutput_4 + "\"");
				}
				String insert_tDBOutput_4 = "INSERT INTO \"" + tableName_tDBOutput_4
						+ "\" (\"idpromotion\",\"date_debut_promotion\",\"date_fin_promotion\",\"prix_promotion\",\"promotion_pourcentage\",\"prix_achat\",\"idarticle\") VALUES (?,?,?,?,?,?,?)";

				java.sql.PreparedStatement pstmt_tDBOutput_4 = conn_tDBOutput_4.prepareStatement(insert_tDBOutput_4);
				resourceMap.put("pstmt_tDBOutput_4", pstmt_tDBOutput_4);

				/**
				 * [tDBOutput_4 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row43");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp = new copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0Struct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_4 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_4", false);
				start_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_4";

				int tos_count_tFileInputDelimited_4 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_4 = new routines.system.RowState();

				class RowHelper_tFileInputDelimited_4 {

					public void valueToConn_0(org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4,
							row43Struct row43) throws java.lang.Exception {

						int columnIndexWithD_tFileInputDelimited_4 = 0;

						columnIndexWithD_tFileInputDelimited_4 = 0;

						row43.PREF = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 1;

						row43.PDESIG1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 2;

						row43.PDESIG2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 3;

						row43.PDESIG3 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 4;

						row43.PPRIX = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 5;

						row43.PCTVA = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 6;

						row43.PCTYV = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 7;

						row43.PCPN = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 8;

						row43.PPA = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 9;

						row43.PQTE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 10;

						row43.PFORM01 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 11;

						row43.PFORM02 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 12;

						row43.PNBCAR = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 13;

						row43.PVERT = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 14;

						row43.PEMP2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 15;

						row43.PLIEU2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 16;

						row43.PREMIMAX = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 17;

						row43.FILLER2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 18;

						row43.PMOUV = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 19;

						row43.PTENU = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 20;

						row43.PINCRE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 21;

						row43.PPROMPA = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 22;

						row43.PACHAT = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 23;

						row43.PARRCDT1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 24;

						row43.PARRCDT2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 25;

						row43.PECOTAXE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 26;

						row43.PPAQUET = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 27;

						row43.PEMBAL = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 28;

						row43.PGRAMA = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 29;

						row43.PDATCRE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 30;

						row43.PDATPRIX = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 31;

						row43.PDATPA1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 32;

						row43.PPA1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 33;

						row43.PPV1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 34;

						row43.PPRIXR = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 35;

						row43.PPRIXRV1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 36;

						row43.PPRIXRV2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 37;

						row43.PPRIXRV3 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 38;

						row43.PPRIREV1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 39;

						row43.PPRIREV2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 40;

						row43.PPRIREV3 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 41;

						row43.PDATPV1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 42;

						row43.PMPA = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 43;

						row43.PETIQ = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 44;

						row43.PFOUR = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 45;

						row43.PPRITTC = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 46;

						row43.PIMOD = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 47;

						row43.PPROMREM = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 48;

						row43.PUV = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 49;

						row43.PUA = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 50;

						row43.PDELAI = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 51;

						row43.PMINI = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 52;

						row43.PMAXI = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 53;

						row43.PCDE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 54;

						row43.PEMP = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 55;

						row43.PPRI1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 56;

						row43.PPRI2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 57;

						row43.PPRI3 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 58;

						row43.PPRI4 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 59;

						row43.PPRI5 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 60;

						row43.PPRI6 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 61;

						row43.PREMI1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 62;

						row43.PREMI2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 63;

						row43.PREMI3 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 64;

						row43.PREMI4 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 65;

						row43.PREMI5 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 66;

						row43.PREMI6 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 67;

						row43.PQUANT1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 68;

						row43.PQUANT2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 69;

						row43.PQUANT3 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 70;

						row43.PQUANT4 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 71;

						row43.PQUANT5 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 72;

						row43.PQUANT6 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 73;

						row43.PEDAT = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 74;

						row43.PSDAT = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 75;

						row43.PRESV = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 76;

						row43.PREFOU = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 77;

						row43.PGAMEC = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 78;

						row43.PGAMER = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 79;

						row43.PGAMEN = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 80;

						row43.PTPF = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 81;

						row43.PSOMMEIL = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 82;

						row43.PINV = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 83;

						row43.PMAC = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 84;

						row43.PREJ = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 85;

						row43.PMOI = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 86;

						row43.P1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 87;

						row43.P2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 88;

						row43.P3 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 89;

						row43.P4 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 90;

						row43.P5 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 91;

						row43.P6 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 92;

						row43.P7 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 93;

						row43.P8 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 94;

						row43.P9 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 95;

						row43.P10 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 96;

						row43.P11 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 97;

						row43.P12 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 98;

						row43.PAN = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 99;

						row43.PAN1 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

					}

					public void valueToConn_1(org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4,
							row43Struct row43) throws java.lang.Exception {

						int columnIndexWithD_tFileInputDelimited_4 = 0;

						columnIndexWithD_tFileInputDelimited_4 = 100;

						row43.PAN2 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 101;

						row43.PPROM = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 102;

						row43.PPROMD = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 103;

						row43.PCONDI = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 104;

						row43.PRESTE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 105;

						row43.PTITR = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 106;

						row43.PPARAG = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 107;

						row43.PPOID = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 108;

						row43.PKLE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 109;

						row43.PLIEN = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 110;

						row43.PSFAM = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 111;

						row43.PREF01 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 112;

						row43.PREF02 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 113;

						row43.PREF03 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 114;

						row43.PTYPE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 115;

						row43.PLIEU = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 116;

						row43.PCATAL = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 117;

						row43.PUV01 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 118;

						row43.PCONDI01 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 119;

						row43.PUV02 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 120;

						row43.PCONDI02 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 121;

						row43.PPROMDEB = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 122;

						row43.PREMFOU = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 123;

						row43.PQTE01 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 124;

						row43.PQTE02 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 125;

						row43.PRX01 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 126;

						row43.PRX02 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 127;

						row43.PCUBAG = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 128;

						row43.PREVPOI = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 129;

						row43.PREVCUB = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 130;

						row43.PREVFAP = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 131;

						row43.PINTERNET = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 132;

						row43.PFIDEL = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

						columnIndexWithD_tFileInputDelimited_4 = 133;

						row43.PCOMMANDE = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

					}

					public void valueToConn(org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4,
							row43Struct row43) throws java.lang.Exception {

						valueToConn_0(fid_tFileInputDelimited_4, row43);

						valueToConn_1(fid_tFileInputDelimited_4, row43);

					}

				}
				RowHelper_tFileInputDelimited_4 rowHelper_tFileInputDelimited_4 = new RowHelper_tFileInputDelimited_4();

				int nb_line_tFileInputDelimited_4 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4 = null;
				int limit_tFileInputDelimited_4 = -1;
				try {

					Object filename_tFileInputDelimited_4 = "C:/DEV/Data/Base Montauban/Article.csv";
					if (filename_tFileInputDelimited_4 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_4 = 0, random_value_tFileInputDelimited_4 = -1;
						if (footer_value_tFileInputDelimited_4 > 0 || random_value_tFileInputDelimited_4 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_4 = new org.talend.fileprocess.FileInputDelimited(
								"C:/DEV/Data/Base Montauban/Article.csv", "ISO-8859-15", ";", "\n", true, 1, 0,
								limit_tFileInputDelimited_4, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_4 != null && fid_tFileInputDelimited_4.nextRecord()) {
						rowstate_tFileInputDelimited_4.reset();

						row43 = null;

						boolean whetherReject_tFileInputDelimited_4 = false;
						row43 = new row43Struct();
						try {

							rowHelper_tFileInputDelimited_4.valueToConn(fid_tFileInputDelimited_4, row43);

							if (rowstate_tFileInputDelimited_4.getException() != null) {
								throw rowstate_tFileInputDelimited_4.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_4 = true;

							System.err.println(e.getMessage());
							row43 = null;

						}

						/**
						 * [tFileInputDelimited_4 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_4 main ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						tos_count_tFileInputDelimited_4++;

						/**
						 * [tFileInputDelimited_4 main ] stop
						 */

						/**
						 * [tFileInputDelimited_4 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						/**
						 * [tFileInputDelimited_4 process_data_begin ] stop
						 */
// Start of branch "row43"
						if (row43 != null) {

							/**
							 * [tMap_4 main ] start
							 */

							currentComponent = "tMap_4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row43"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_4 = false;
							boolean mainRowRejected_tMap_4 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
								// ###############################
								// # Output tables

								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0 = null;

// # Output table : 'copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0'
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.idpromotion = Numeric
										.sequence("promotion", 1, 1);
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.date_debut_promotion = row43.PPROMDEB;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.date_fin_promotion = row43.PPROMD;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.prix_promotion = Float
										.parseFloat(row43.PPROM);
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.promotion_pourcentage = Float
										.parseFloat(row43.PPROMREM);
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.prix_achat = Float
										.parseFloat(row43.PPROMPA);
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp.idarticle = row43.PREF;
								copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0 = copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_4 = false;

							tos_count_tMap_4++;

							/**
							 * [tMap_4 main ] stop
							 */

							/**
							 * [tMap_4 process_data_begin ] start
							 */

							currentComponent = "tMap_4";

							/**
							 * [tMap_4 process_data_begin ] stop
							 */
// Start of branch "copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0"
							if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0 != null) {

								/**
								 * [tDBOutput_4 main ] start
								 */

								currentComponent = "tDBOutput_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0"

									);
								}

								whetherReject_tDBOutput_4 = false;
								pstmt_tDBOutput_4.setInt(1, copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.idpromotion);

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.date_debut_promotion == null) {
									pstmt_tDBOutput_4.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_4.setString(2,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.date_debut_promotion);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.date_fin_promotion == null) {
									pstmt_tDBOutput_4.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_4.setString(3,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.date_fin_promotion);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.prix_promotion == null) {
									pstmt_tDBOutput_4.setNull(4, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_4.setFloat(4,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.prix_promotion);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.promotion_pourcentage == null) {
									pstmt_tDBOutput_4.setNull(5, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_4.setFloat(5,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.promotion_pourcentage);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.prix_achat == null) {
									pstmt_tDBOutput_4.setNull(6, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_4.setFloat(6,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.prix_achat);
								}

								if (copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.idarticle == null) {
									pstmt_tDBOutput_4.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_4.setString(7,
											copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0.idarticle);
								}

								pstmt_tDBOutput_4.addBatch();
								nb_line_tDBOutput_4++;

								batchSizeCounter_tDBOutput_4++;

								if ((batchSize_tDBOutput_4 > 0)
										&& (batchSize_tDBOutput_4 <= batchSizeCounter_tDBOutput_4)) {
									try {
										int countSum_tDBOutput_4 = 0;

										for (int countEach_tDBOutput_4 : pstmt_tDBOutput_4.executeBatch()) {
											countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0
													: countEach_tDBOutput_4);
										}
										rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;

										insertedCount_tDBOutput_4 += countSum_tDBOutput_4;

										batchSizeCounter_tDBOutput_4 = 0;
									} catch (java.sql.BatchUpdateException e_tDBOutput_4) {
										globalMap.put("tDBOutput_4_ERROR_MESSAGE", e_tDBOutput_4.getMessage());
										java.sql.SQLException ne_tDBOutput_4 = e_tDBOutput_4.getNextException(),
												sqle_tDBOutput_4 = null;
										String errormessage_tDBOutput_4;
										if (ne_tDBOutput_4 != null) {
											// build new exception to provide the original cause
											sqle_tDBOutput_4 = new java.sql.SQLException(
													e_tDBOutput_4.getMessage() + "\ncaused by: "
															+ ne_tDBOutput_4.getMessage(),
													ne_tDBOutput_4.getSQLState(), ne_tDBOutput_4.getErrorCode(),
													ne_tDBOutput_4);
											errormessage_tDBOutput_4 = sqle_tDBOutput_4.getMessage();
										} else {
											errormessage_tDBOutput_4 = e_tDBOutput_4.getMessage();
										}

										int countSum_tDBOutput_4 = 0;
										for (int countEach_tDBOutput_4 : e_tDBOutput_4.getUpdateCounts()) {
											countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0
													: countEach_tDBOutput_4);
										}
										rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;

										insertedCount_tDBOutput_4 += countSum_tDBOutput_4;

										System.err.println(errormessage_tDBOutput_4);

									}
								}

								tos_count_tDBOutput_4++;

								/**
								 * [tDBOutput_4 main ] stop
								 */

								/**
								 * [tDBOutput_4 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_4";

								/**
								 * [tDBOutput_4 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_4 process_data_end ] start
								 */

								currentComponent = "tDBOutput_4";

								/**
								 * [tDBOutput_4 process_data_end ] stop
								 */

							} // End of branch "copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0"

							/**
							 * [tMap_4 process_data_end ] start
							 */

							currentComponent = "tMap_4";

							/**
							 * [tMap_4 process_data_end ] stop
							 */

						} // End of branch "row43"

						/**
						 * [tFileInputDelimited_4 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						/**
						 * [tFileInputDelimited_4 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_4 end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

					}
				} finally {
					if (!((Object) ("C:/DEV/Data/Base Montauban/Article.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_4 != null) {
							fid_tFileInputDelimited_4.close();
						}
					}
					if (fid_tFileInputDelimited_4 != null) {
						globalMap.put("tFileInputDelimited_4_NB_LINE", fid_tFileInputDelimited_4.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_4", true);
				end_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_4 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row43");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
				 */

				/**
				 * [tDBOutput_4 end ] start
				 */

				currentComponent = "tDBOutput_4";

				try {
					int countSum_tDBOutput_4 = 0;
					if (pstmt_tDBOutput_4 != null && batchSizeCounter_tDBOutput_4 > 0) {

						for (int countEach_tDBOutput_4 : pstmt_tDBOutput_4.executeBatch()) {
							countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0 : countEach_tDBOutput_4);
						}
						rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;

					}

					insertedCount_tDBOutput_4 += countSum_tDBOutput_4;

				} catch (java.sql.BatchUpdateException e_tDBOutput_4) {
					globalMap.put("tDBOutput_4_ERROR_MESSAGE", e_tDBOutput_4.getMessage());
					java.sql.SQLException ne_tDBOutput_4 = e_tDBOutput_4.getNextException(), sqle_tDBOutput_4 = null;
					String errormessage_tDBOutput_4;
					if (ne_tDBOutput_4 != null) {
						// build new exception to provide the original cause
						sqle_tDBOutput_4 = new java.sql.SQLException(
								e_tDBOutput_4.getMessage() + "\ncaused by: " + ne_tDBOutput_4.getMessage(),
								ne_tDBOutput_4.getSQLState(), ne_tDBOutput_4.getErrorCode(), ne_tDBOutput_4);
						errormessage_tDBOutput_4 = sqle_tDBOutput_4.getMessage();
					} else {
						errormessage_tDBOutput_4 = e_tDBOutput_4.getMessage();
					}

					int countSum_tDBOutput_4 = 0;
					for (int countEach_tDBOutput_4 : e_tDBOutput_4.getUpdateCounts()) {
						countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0 : countEach_tDBOutput_4);
					}
					rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;

					insertedCount_tDBOutput_4 += countSum_tDBOutput_4;

					System.err.println(errormessage_tDBOutput_4);

				}

				if (pstmt_tDBOutput_4 != null) {

					pstmt_tDBOutput_4.close();
					resourceMap.remove("pstmt_tDBOutput_4");
				}
				resourceMap.put("statementClosed_tDBOutput_4", true);

				nb_line_deleted_tDBOutput_4 = nb_line_deleted_tDBOutput_4 + deletedCount_tDBOutput_4;
				nb_line_update_tDBOutput_4 = nb_line_update_tDBOutput_4 + updatedCount_tDBOutput_4;
				nb_line_inserted_tDBOutput_4 = nb_line_inserted_tDBOutput_4 + insertedCount_tDBOutput_4;
				nb_line_rejected_tDBOutput_4 = nb_line_rejected_tDBOutput_4 + rejectedCount_tDBOutput_4;

				globalMap.put("tDBOutput_4_NB_LINE", nb_line_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_UPDATED", nb_line_update_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_DELETED", nb_line_deleted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_4);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "copyOfcopyOfcopyOfcopyOfcopyOfcopyOfmain_2_0");
				}

				ok_Hash.put("tDBOutput_4", true);
				end_Hash.put("tDBOutput_4", System.currentTimeMillis());

				/**
				 * [tDBOutput_4 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_4:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk8", 0, "ok");
			}

			tFileInputDelimited_5Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_4 finally ] start
				 */

				currentComponent = "tFileInputDelimited_4";

				/**
				 * [tFileInputDelimited_4 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tDBOutput_4 finally ] start
				 */

				currentComponent = "tDBOutput_4";

				if (resourceMap.get("statementClosed_tDBOutput_4") == null) {
					java.sql.PreparedStatement pstmtToClose_tDBOutput_4 = null;
					if ((pstmtToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_4")) != null) {
						pstmtToClose_tDBOutput_4.close();
					}
				}

				/**
				 * [tDBOutput_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 1);
	}

	public static class copyOfcopyOfcopyOfcopyOfmain_2_3Struct
			implements routines.system.IPersistableRow<copyOfcopyOfcopyOfcopyOfmain_2_3Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int idmouvement_stock;

		public int getIdmouvement_stock() {
			return this.idmouvement_stock;
		}

		public Float ppa;

		public Float getPpa() {
			return this.ppa;
		}

		public Float pv;

		public Float getPv() {
			return this.pv;
		}

		public String codemvt;

		public String getCodemvt() {
			return this.codemvt;
		}

		public String date;

		public String getDate() {
			return this.date;
		}

		public Float qte;

		public Float getQte() {
			return this.qte;
		}

		public String saisie;

		public String getSaisie() {
			return this.saisie;
		}

		public String soc;

		public String getSoc() {
			return this.soc;
		}

		public String typedoc;

		public String getTypedoc() {
			return this.typedoc;
		}

		public String idarticle;

		public String getIdarticle() {
			return this.idarticle;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.idmouvement_stock;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final copyOfcopyOfcopyOfcopyOfmain_2_3Struct other = (copyOfcopyOfcopyOfcopyOfmain_2_3Struct) obj;

			if (this.idmouvement_stock != other.idmouvement_stock)
				return false;

			return true;
		}

		public void copyDataTo(copyOfcopyOfcopyOfcopyOfmain_2_3Struct other) {

			other.idmouvement_stock = this.idmouvement_stock;
			other.ppa = this.ppa;
			other.pv = this.pv;
			other.codemvt = this.codemvt;
			other.date = this.date;
			other.qte = this.qte;
			other.saisie = this.saisie;
			other.soc = this.soc;
			other.typedoc = this.typedoc;
			other.idarticle = this.idarticle;

		}

		public void copyKeysDataTo(copyOfcopyOfcopyOfcopyOfmain_2_3Struct other) {

			other.idmouvement_stock = this.idmouvement_stock;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idmouvement_stock = dis.readInt();

					length = dis.readByte();
					if (length == -1) {
						this.ppa = null;
					} else {
						this.ppa = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.pv = null;
					} else {
						this.pv = dis.readFloat();
					}

					this.codemvt = readString(dis);

					this.date = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.qte = null;
					} else {
						this.qte = dis.readFloat();
					}

					this.saisie = readString(dis);

					this.soc = readString(dis);

					this.typedoc = readString(dis);

					this.idarticle = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.idmouvement_stock = dis.readInt();

					length = dis.readByte();
					if (length == -1) {
						this.ppa = null;
					} else {
						this.ppa = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.pv = null;
					} else {
						this.pv = dis.readFloat();
					}

					this.codemvt = readString(dis);

					this.date = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.qte = null;
					} else {
						this.qte = dis.readFloat();
					}

					this.saisie = readString(dis);

					this.soc = readString(dis);

					this.typedoc = readString(dis);

					this.idarticle = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.idmouvement_stock);

				// Float

				if (this.ppa == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.ppa);
				}

				// Float

				if (this.pv == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.pv);
				}

				// String

				writeString(this.codemvt, dos);

				// String

				writeString(this.date, dos);

				// Float

				if (this.qte == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.qte);
				}

				// String

				writeString(this.saisie, dos);

				// String

				writeString(this.soc, dos);

				// String

				writeString(this.typedoc, dos);

				// String

				writeString(this.idarticle, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.idmouvement_stock);

				// Float

				if (this.ppa == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.ppa);
				}

				// Float

				if (this.pv == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.pv);
				}

				// String

				writeString(this.codemvt, dos);

				// String

				writeString(this.date, dos);

				// Float

				if (this.qte == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.qte);
				}

				// String

				writeString(this.saisie, dos);

				// String

				writeString(this.soc, dos);

				// String

				writeString(this.typedoc, dos);

				// String

				writeString(this.idarticle, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("idmouvement_stock=" + String.valueOf(idmouvement_stock));
			sb.append(",ppa=" + String.valueOf(ppa));
			sb.append(",pv=" + String.valueOf(pv));
			sb.append(",codemvt=" + codemvt);
			sb.append(",date=" + date);
			sb.append(",qte=" + String.valueOf(qte));
			sb.append(",saisie=" + saisie);
			sb.append(",soc=" + soc);
			sb.append(",typedoc=" + typedoc);
			sb.append(",idarticle=" + idarticle);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(copyOfcopyOfcopyOfcopyOfmain_2_3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.idmouvement_stock, other.idmouvement_stock);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row44Struct implements routines.system.IPersistableRow<row44Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];

		public String STOREF;

		public String getSTOREF() {
			return this.STOREF;
		}

		public String STOAN;

		public String getSTOAN() {
			return this.STOAN;
		}

		public String STOMM;

		public String getSTOMM() {
			return this.STOMM;
		}

		public String STOJJ;

		public String getSTOJJ() {
			return this.STOJJ;
		}

		public String STOTYP;

		public String getSTOTYP() {
			return this.STOTYP;
		}

		public String STONUM;

		public String getSTONUM() {
			return this.STONUM;
		}

		public String STOQTE;

		public String getSTOQTE() {
			return this.STOQTE;
		}

		public String STOPA;

		public String getSTOPA() {
			return this.STOPA;
		}

		public String STOPV;

		public String getSTOPV() {
			return this.STOPV;
		}

		public String STOFNS;

		public String getSTOFNS() {
			return this.STOFNS;
		}

		public String STODOC;

		public String getSTODOC() {
			return this.STODOC;
		}

		public String STOMVT;

		public String getSTOMVT() {
			return this.STOMVT;
		}

		public String STOSOC;

		public String getSTOSOC() {
			return this.STOSOC;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.STOREF = readString(dis);

					this.STOAN = readString(dis);

					this.STOMM = readString(dis);

					this.STOJJ = readString(dis);

					this.STOTYP = readString(dis);

					this.STONUM = readString(dis);

					this.STOQTE = readString(dis);

					this.STOPA = readString(dis);

					this.STOPV = readString(dis);

					this.STOFNS = readString(dis);

					this.STODOC = readString(dis);

					this.STOMVT = readString(dis);

					this.STOSOC = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.STOREF = readString(dis);

					this.STOAN = readString(dis);

					this.STOMM = readString(dis);

					this.STOJJ = readString(dis);

					this.STOTYP = readString(dis);

					this.STONUM = readString(dis);

					this.STOQTE = readString(dis);

					this.STOPA = readString(dis);

					this.STOPV = readString(dis);

					this.STOFNS = readString(dis);

					this.STODOC = readString(dis);

					this.STOMVT = readString(dis);

					this.STOSOC = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.STOREF, dos);

				// String

				writeString(this.STOAN, dos);

				// String

				writeString(this.STOMM, dos);

				// String

				writeString(this.STOJJ, dos);

				// String

				writeString(this.STOTYP, dos);

				// String

				writeString(this.STONUM, dos);

				// String

				writeString(this.STOQTE, dos);

				// String

				writeString(this.STOPA, dos);

				// String

				writeString(this.STOPV, dos);

				// String

				writeString(this.STOFNS, dos);

				// String

				writeString(this.STODOC, dos);

				// String

				writeString(this.STOMVT, dos);

				// String

				writeString(this.STOSOC, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.STOREF, dos);

				// String

				writeString(this.STOAN, dos);

				// String

				writeString(this.STOMM, dos);

				// String

				writeString(this.STOJJ, dos);

				// String

				writeString(this.STOTYP, dos);

				// String

				writeString(this.STONUM, dos);

				// String

				writeString(this.STOQTE, dos);

				// String

				writeString(this.STOPA, dos);

				// String

				writeString(this.STOPV, dos);

				// String

				writeString(this.STOFNS, dos);

				// String

				writeString(this.STODOC, dos);

				// String

				writeString(this.STOMVT, dos);

				// String

				writeString(this.STOSOC, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("STOREF=" + STOREF);
			sb.append(",STOAN=" + STOAN);
			sb.append(",STOMM=" + STOMM);
			sb.append(",STOJJ=" + STOJJ);
			sb.append(",STOTYP=" + STOTYP);
			sb.append(",STONUM=" + STONUM);
			sb.append(",STOQTE=" + STOQTE);
			sb.append(",STOPA=" + STOPA);
			sb.append(",STOPV=" + STOPV);
			sb.append(",STOFNS=" + STOFNS);
			sb.append(",STODOC=" + STODOC);
			sb.append(",STOMVT=" + STOMVT);
			sb.append(",STOSOC=" + STOSOC);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row44Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_5Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_5Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];

		public String STOREF;

		public String getSTOREF() {
			return this.STOREF;
		}

		public String STOAN;

		public String getSTOAN() {
			return this.STOAN;
		}

		public String STOMM;

		public String getSTOMM() {
			return this.STOMM;
		}

		public String STOJJ;

		public String getSTOJJ() {
			return this.STOJJ;
		}

		public String STOTYP;

		public String getSTOTYP() {
			return this.STOTYP;
		}

		public String STONUM;

		public String getSTONUM() {
			return this.STONUM;
		}

		public String STOQTE;

		public String getSTOQTE() {
			return this.STOQTE;
		}

		public String STOPA;

		public String getSTOPA() {
			return this.STOPA;
		}

		public String STOPV;

		public String getSTOPV() {
			return this.STOPV;
		}

		public String STOFNS;

		public String getSTOFNS() {
			return this.STOFNS;
		}

		public String STODOC;

		public String getSTODOC() {
			return this.STODOC;
		}

		public String STOMVT;

		public String getSTOMVT() {
			return this.STOMVT;
		}

		public String STOSOC;

		public String getSTOSOC() {
			return this.STOSOC;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.STOREF = readString(dis);

					this.STOAN = readString(dis);

					this.STOMM = readString(dis);

					this.STOJJ = readString(dis);

					this.STOTYP = readString(dis);

					this.STONUM = readString(dis);

					this.STOQTE = readString(dis);

					this.STOPA = readString(dis);

					this.STOPV = readString(dis);

					this.STOFNS = readString(dis);

					this.STODOC = readString(dis);

					this.STOMVT = readString(dis);

					this.STOSOC = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.STOREF = readString(dis);

					this.STOAN = readString(dis);

					this.STOMM = readString(dis);

					this.STOJJ = readString(dis);

					this.STOTYP = readString(dis);

					this.STONUM = readString(dis);

					this.STOQTE = readString(dis);

					this.STOPA = readString(dis);

					this.STOPV = readString(dis);

					this.STOFNS = readString(dis);

					this.STODOC = readString(dis);

					this.STOMVT = readString(dis);

					this.STOSOC = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.STOREF, dos);

				// String

				writeString(this.STOAN, dos);

				// String

				writeString(this.STOMM, dos);

				// String

				writeString(this.STOJJ, dos);

				// String

				writeString(this.STOTYP, dos);

				// String

				writeString(this.STONUM, dos);

				// String

				writeString(this.STOQTE, dos);

				// String

				writeString(this.STOPA, dos);

				// String

				writeString(this.STOPV, dos);

				// String

				writeString(this.STOFNS, dos);

				// String

				writeString(this.STODOC, dos);

				// String

				writeString(this.STOMVT, dos);

				// String

				writeString(this.STOSOC, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.STOREF, dos);

				// String

				writeString(this.STOAN, dos);

				// String

				writeString(this.STOMM, dos);

				// String

				writeString(this.STOJJ, dos);

				// String

				writeString(this.STOTYP, dos);

				// String

				writeString(this.STONUM, dos);

				// String

				writeString(this.STOQTE, dos);

				// String

				writeString(this.STOPA, dos);

				// String

				writeString(this.STOPV, dos);

				// String

				writeString(this.STOFNS, dos);

				// String

				writeString(this.STODOC, dos);

				// String

				writeString(this.STOMVT, dos);

				// String

				writeString(this.STOSOC, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("STOREF=" + STOREF);
			sb.append(",STOAN=" + STOAN);
			sb.append(",STOMM=" + STOMM);
			sb.append(",STOJJ=" + STOJJ);
			sb.append(",STOTYP=" + STOTYP);
			sb.append(",STONUM=" + STONUM);
			sb.append(",STOQTE=" + STOQTE);
			sb.append(",STOPA=" + STOPA);
			sb.append(",STOPV=" + STOPV);
			sb.append(",STOFNS=" + STOFNS);
			sb.append(",STODOC=" + STODOC);
			sb.append(",STOMVT=" + STOMVT);
			sb.append(",STOSOC=" + STOSOC);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_6Process(globalMap);

				row44Struct row44 = new row44Struct();
				copyOfcopyOfcopyOfcopyOfmain_2_3Struct copyOfcopyOfcopyOfcopyOfmain_2_3 = new copyOfcopyOfcopyOfcopyOfmain_2_3Struct();

				/**
				 * [tDBOutput_5 begin ] start
				 */

				ok_Hash.put("tDBOutput_5", false);
				start_Hash.put("tDBOutput_5", System.currentTimeMillis());

				currentComponent = "tDBOutput_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "copyOfcopyOfcopyOfcopyOfmain_2_3");
				}

				int tos_count_tDBOutput_5 = 0;

				String dbschema_tDBOutput_5 = null;
				dbschema_tDBOutput_5 = (String) globalMap.get("schema_" + "tDBConnection_1");

				String tableName_tDBOutput_5 = null;
				if (dbschema_tDBOutput_5 == null || dbschema_tDBOutput_5.trim().length() == 0) {
					tableName_tDBOutput_5 = ("mouvement_stock");
				} else {
					tableName_tDBOutput_5 = dbschema_tDBOutput_5 + "\".\"" + ("mouvement_stock");
				}

				int nb_line_tDBOutput_5 = 0;
				int nb_line_update_tDBOutput_5 = 0;
				int nb_line_inserted_tDBOutput_5 = 0;
				int nb_line_deleted_tDBOutput_5 = 0;
				int nb_line_rejected_tDBOutput_5 = 0;

				int deletedCount_tDBOutput_5 = 0;
				int updatedCount_tDBOutput_5 = 0;
				int insertedCount_tDBOutput_5 = 0;
				int rowsToCommitCount_tDBOutput_5 = 0;
				int rejectedCount_tDBOutput_5 = 0;

				boolean whetherReject_tDBOutput_5 = false;

				java.sql.Connection conn_tDBOutput_5 = null;
				String dbUser_tDBOutput_5 = null;

				conn_tDBOutput_5 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");

				int batchSize_tDBOutput_5 = 10000;
				int batchSizeCounter_tDBOutput_5 = 0;

				int count_tDBOutput_5 = 0;
				try (java.sql.Statement stmtClear_tDBOutput_5 = conn_tDBOutput_5.createStatement()) {
					stmtClear_tDBOutput_5.executeUpdate("DELETE FROM \"" + tableName_tDBOutput_5 + "\"");
				}
				String insert_tDBOutput_5 = "INSERT INTO \"" + tableName_tDBOutput_5
						+ "\" (\"idmouvement_stock\",\"ppa\",\"pv\",\"codemvt\",\"date\",\"qte\",\"saisie\",\"soc\",\"typedoc\",\"idarticle\") VALUES (?,?,?,?,?,?,?,?,?,?)";

				java.sql.PreparedStatement pstmt_tDBOutput_5 = conn_tDBOutput_5.prepareStatement(insert_tDBOutput_5);
				resourceMap.put("pstmt_tDBOutput_5", pstmt_tDBOutput_5);

				/**
				 * [tDBOutput_5 begin ] stop
				 */

				/**
				 * [tMap_5 begin ] start
				 */

				ok_Hash.put("tMap_5", false);
				start_Hash.put("tMap_5", System.currentTimeMillis());

				currentComponent = "tMap_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row44");
				}

				int tos_count_tMap_5 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row45Struct> tHash_Lookup_row45 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row45Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row45Struct>) globalMap
						.get("tHash_Lookup_row45"));

				row45Struct row45HashKey = new row45Struct();
				row45Struct row45Default = new row45Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_5__Struct {
				}
				Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				copyOfcopyOfcopyOfcopyOfmain_2_3Struct copyOfcopyOfcopyOfcopyOfmain_2_3_tmp = new copyOfcopyOfcopyOfcopyOfmain_2_3Struct();
// ###############################

				/**
				 * [tMap_5 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_5 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_5", false);
				start_Hash.put("tFileInputDelimited_5", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_5";

				int tos_count_tFileInputDelimited_5 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_5 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_5 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_5 = null;
				int limit_tFileInputDelimited_5 = -1;
				try {

					Object filename_tFileInputDelimited_5 = "C:/DEV/Data/Base Montauban/mouvements_de_stock.csv";
					if (filename_tFileInputDelimited_5 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_5 = 0, random_value_tFileInputDelimited_5 = -1;
						if (footer_value_tFileInputDelimited_5 > 0 || random_value_tFileInputDelimited_5 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_5 = new org.talend.fileprocess.FileInputDelimited(
								"C:/DEV/Data/Base Montauban/mouvements_de_stock.csv", "ISO-8859-15", ";", "\n", true, 1,
								0, limit_tFileInputDelimited_5, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_5 != null && fid_tFileInputDelimited_5.nextRecord()) {
						rowstate_tFileInputDelimited_5.reset();

						row44 = null;

						boolean whetherReject_tFileInputDelimited_5 = false;
						row44 = new row44Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_5 = 0;

							columnIndexWithD_tFileInputDelimited_5 = 0;

							row44.STOREF = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 1;

							row44.STOAN = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 2;

							row44.STOMM = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 3;

							row44.STOJJ = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 4;

							row44.STOTYP = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 5;

							row44.STONUM = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 6;

							row44.STOQTE = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 7;

							row44.STOPA = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 8;

							row44.STOPV = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 9;

							row44.STOFNS = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 10;

							row44.STODOC = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 11;

							row44.STOMVT = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 12;

							row44.STOSOC = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							if (rowstate_tFileInputDelimited_5.getException() != null) {
								throw rowstate_tFileInputDelimited_5.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_5 = true;

							System.err.println(e.getMessage());
							row44 = null;

						}

						/**
						 * [tFileInputDelimited_5 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_5 main ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						tos_count_tFileInputDelimited_5++;

						/**
						 * [tFileInputDelimited_5 main ] stop
						 */

						/**
						 * [tFileInputDelimited_5 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						/**
						 * [tFileInputDelimited_5 process_data_begin ] stop
						 */
// Start of branch "row44"
						if (row44 != null) {

							/**
							 * [tMap_5 main ] start
							 */

							currentComponent = "tMap_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row44"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_5 = false;
							boolean mainRowRejected_tMap_5 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row45"
							///////////////////////////////////////////////

							boolean forceLooprow45 = false;

							row45Struct row45ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_5) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_5 = false;

								row45HashKey.PREF = row44.STOREF;

								row45HashKey.hashCodeDirty = true;

								tHash_Lookup_row45.lookup(row45HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row45 != null && tHash_Lookup_row45.getCount(row45HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
								// 'row45' and it contains more one result from keys : row45.PREF = '" +
								// row45HashKey.PREF + "'");
							} // G 071

							row45Struct row45 = null;

							row45Struct fromLookup_row45 = null;
							row45 = row45Default;

							if (tHash_Lookup_row45 != null && tHash_Lookup_row45.hasNext()) { // G 099

								fromLookup_row45 = tHash_Lookup_row45.next();

							} // G 099

							if (fromLookup_row45 != null) {
								row45 = fromLookup_row45;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_5__Struct Var = Var__tMap_5;// ###############################
								// ###############################
								// # Output tables

								copyOfcopyOfcopyOfcopyOfmain_2_3 = null;

// # Output table : 'copyOfcopyOfcopyOfcopyOfmain_2_3'
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.idmouvement_stock = Numeric
										.sequence("movementstock", 1, 1);
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.ppa = Float.parseFloat(row44.STOPA);
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.pv = Float.parseFloat(row44.STOPV);
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.codemvt = row44.STOMVT;
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.date = row44.STOJJ + "/" + row44.STOMM + "/"
										+ row44.STOAN;
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.qte = Float.parseFloat(row44.STOQTE);
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.saisie = row44.STOFNS;
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.soc = row44.STOSOC;
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.typedoc = row44.STODOC;
								copyOfcopyOfcopyOfcopyOfmain_2_3_tmp.idarticle = row45.PREF;
								copyOfcopyOfcopyOfcopyOfmain_2_3 = copyOfcopyOfcopyOfcopyOfmain_2_3_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_5 = false;

							tos_count_tMap_5++;

							/**
							 * [tMap_5 main ] stop
							 */

							/**
							 * [tMap_5 process_data_begin ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_begin ] stop
							 */
// Start of branch "copyOfcopyOfcopyOfcopyOfmain_2_3"
							if (copyOfcopyOfcopyOfcopyOfmain_2_3 != null) {

								/**
								 * [tDBOutput_5 main ] start
								 */

								currentComponent = "tDBOutput_5";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "copyOfcopyOfcopyOfcopyOfmain_2_3"

									);
								}

								whetherReject_tDBOutput_5 = false;
								pstmt_tDBOutput_5.setInt(1, copyOfcopyOfcopyOfcopyOfmain_2_3.idmouvement_stock);

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.ppa == null) {
									pstmt_tDBOutput_5.setNull(2, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_5.setFloat(2, copyOfcopyOfcopyOfcopyOfmain_2_3.ppa);
								}

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.pv == null) {
									pstmt_tDBOutput_5.setNull(3, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_5.setFloat(3, copyOfcopyOfcopyOfcopyOfmain_2_3.pv);
								}

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.codemvt == null) {
									pstmt_tDBOutput_5.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_5.setString(4, copyOfcopyOfcopyOfcopyOfmain_2_3.codemvt);
								}

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.date == null) {
									pstmt_tDBOutput_5.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_5.setString(5, copyOfcopyOfcopyOfcopyOfmain_2_3.date);
								}

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.qte == null) {
									pstmt_tDBOutput_5.setNull(6, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_5.setFloat(6, copyOfcopyOfcopyOfcopyOfmain_2_3.qte);
								}

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.saisie == null) {
									pstmt_tDBOutput_5.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_5.setString(7, copyOfcopyOfcopyOfcopyOfmain_2_3.saisie);
								}

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.soc == null) {
									pstmt_tDBOutput_5.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_5.setString(8, copyOfcopyOfcopyOfcopyOfmain_2_3.soc);
								}

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.typedoc == null) {
									pstmt_tDBOutput_5.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_5.setString(9, copyOfcopyOfcopyOfcopyOfmain_2_3.typedoc);
								}

								if (copyOfcopyOfcopyOfcopyOfmain_2_3.idarticle == null) {
									pstmt_tDBOutput_5.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_5.setString(10, copyOfcopyOfcopyOfcopyOfmain_2_3.idarticle);
								}

								pstmt_tDBOutput_5.addBatch();
								nb_line_tDBOutput_5++;

								batchSizeCounter_tDBOutput_5++;

								if (!whetherReject_tDBOutput_5) {
								}
								if ((batchSize_tDBOutput_5 > 0)
										&& (batchSize_tDBOutput_5 <= batchSizeCounter_tDBOutput_5)) {
									try {
										int countSum_tDBOutput_5 = 0;

										for (int countEach_tDBOutput_5 : pstmt_tDBOutput_5.executeBatch()) {
											countSum_tDBOutput_5 += (countEach_tDBOutput_5 < 0 ? 0
													: countEach_tDBOutput_5);
										}
										rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;

										insertedCount_tDBOutput_5 += countSum_tDBOutput_5;

										batchSizeCounter_tDBOutput_5 = 0;
									} catch (java.sql.BatchUpdateException e_tDBOutput_5) {
										globalMap.put("tDBOutput_5_ERROR_MESSAGE", e_tDBOutput_5.getMessage());
										java.sql.SQLException ne_tDBOutput_5 = e_tDBOutput_5.getNextException(),
												sqle_tDBOutput_5 = null;
										String errormessage_tDBOutput_5;
										if (ne_tDBOutput_5 != null) {
											// build new exception to provide the original cause
											sqle_tDBOutput_5 = new java.sql.SQLException(
													e_tDBOutput_5.getMessage() + "\ncaused by: "
															+ ne_tDBOutput_5.getMessage(),
													ne_tDBOutput_5.getSQLState(), ne_tDBOutput_5.getErrorCode(),
													ne_tDBOutput_5);
											errormessage_tDBOutput_5 = sqle_tDBOutput_5.getMessage();
										} else {
											errormessage_tDBOutput_5 = e_tDBOutput_5.getMessage();
										}

										int countSum_tDBOutput_5 = 0;
										for (int countEach_tDBOutput_5 : e_tDBOutput_5.getUpdateCounts()) {
											countSum_tDBOutput_5 += (countEach_tDBOutput_5 < 0 ? 0
													: countEach_tDBOutput_5);
										}
										rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;

										insertedCount_tDBOutput_5 += countSum_tDBOutput_5;

										System.err.println(errormessage_tDBOutput_5);

									}
								}

								tos_count_tDBOutput_5++;

								/**
								 * [tDBOutput_5 main ] stop
								 */

								/**
								 * [tDBOutput_5 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_5";

								/**
								 * [tDBOutput_5 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_5 process_data_end ] start
								 */

								currentComponent = "tDBOutput_5";

								/**
								 * [tDBOutput_5 process_data_end ] stop
								 */

							} // End of branch "copyOfcopyOfcopyOfcopyOfmain_2_3"

							/**
							 * [tMap_5 process_data_end ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_end ] stop
							 */

						} // End of branch "row44"

						/**
						 * [tFileInputDelimited_5 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						/**
						 * [tFileInputDelimited_5 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_5 end ] start
						 */

						currentComponent = "tFileInputDelimited_5";

					}
				} finally {
					if (!((Object) ("C:/DEV/Data/Base Montauban/mouvements_de_stock.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_5 != null) {
							fid_tFileInputDelimited_5.close();
						}
					}
					if (fid_tFileInputDelimited_5 != null) {
						globalMap.put("tFileInputDelimited_5_NB_LINE", fid_tFileInputDelimited_5.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_5", true);
				end_Hash.put("tFileInputDelimited_5", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_5 end ] stop
				 */

				/**
				 * [tMap_5 end ] start
				 */

				currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row45 != null) {
					tHash_Lookup_row45.endGet();
				}
				globalMap.remove("tHash_Lookup_row45");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row44");
				}

				ok_Hash.put("tMap_5", true);
				end_Hash.put("tMap_5", System.currentTimeMillis());

				/**
				 * [tMap_5 end ] stop
				 */

				/**
				 * [tDBOutput_5 end ] start
				 */

				currentComponent = "tDBOutput_5";

				try {
					int countSum_tDBOutput_5 = 0;
					if (pstmt_tDBOutput_5 != null && batchSizeCounter_tDBOutput_5 > 0) {

						for (int countEach_tDBOutput_5 : pstmt_tDBOutput_5.executeBatch()) {
							countSum_tDBOutput_5 += (countEach_tDBOutput_5 < 0 ? 0 : countEach_tDBOutput_5);
						}
						rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;

					}

					insertedCount_tDBOutput_5 += countSum_tDBOutput_5;

				} catch (java.sql.BatchUpdateException e_tDBOutput_5) {
					globalMap.put("tDBOutput_5_ERROR_MESSAGE", e_tDBOutput_5.getMessage());
					java.sql.SQLException ne_tDBOutput_5 = e_tDBOutput_5.getNextException(), sqle_tDBOutput_5 = null;
					String errormessage_tDBOutput_5;
					if (ne_tDBOutput_5 != null) {
						// build new exception to provide the original cause
						sqle_tDBOutput_5 = new java.sql.SQLException(
								e_tDBOutput_5.getMessage() + "\ncaused by: " + ne_tDBOutput_5.getMessage(),
								ne_tDBOutput_5.getSQLState(), ne_tDBOutput_5.getErrorCode(), ne_tDBOutput_5);
						errormessage_tDBOutput_5 = sqle_tDBOutput_5.getMessage();
					} else {
						errormessage_tDBOutput_5 = e_tDBOutput_5.getMessage();
					}

					int countSum_tDBOutput_5 = 0;
					for (int countEach_tDBOutput_5 : e_tDBOutput_5.getUpdateCounts()) {
						countSum_tDBOutput_5 += (countEach_tDBOutput_5 < 0 ? 0 : countEach_tDBOutput_5);
					}
					rowsToCommitCount_tDBOutput_5 += countSum_tDBOutput_5;

					insertedCount_tDBOutput_5 += countSum_tDBOutput_5;

					System.err.println(errormessage_tDBOutput_5);

				}

				if (pstmt_tDBOutput_5 != null) {

					pstmt_tDBOutput_5.close();
					resourceMap.remove("pstmt_tDBOutput_5");
				}
				resourceMap.put("statementClosed_tDBOutput_5", true);

				nb_line_deleted_tDBOutput_5 = nb_line_deleted_tDBOutput_5 + deletedCount_tDBOutput_5;
				nb_line_update_tDBOutput_5 = nb_line_update_tDBOutput_5 + updatedCount_tDBOutput_5;
				nb_line_inserted_tDBOutput_5 = nb_line_inserted_tDBOutput_5 + insertedCount_tDBOutput_5;
				nb_line_rejected_tDBOutput_5 = nb_line_rejected_tDBOutput_5 + rejectedCount_tDBOutput_5;

				globalMap.put("tDBOutput_5_NB_LINE", nb_line_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_UPDATED", nb_line_update_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_DELETED", nb_line_deleted_tDBOutput_5);
				globalMap.put("tDBOutput_5_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_5);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "copyOfcopyOfcopyOfcopyOfmain_2_3");
				}

				ok_Hash.put("tDBOutput_5", true);
				end_Hash.put("tDBOutput_5", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk1", 0, "ok");
				}
				tDBCommit_1Process(globalMap);

				/**
				 * [tDBOutput_5 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_5"
			globalMap.remove("tHash_Lookup_row45");

			try {

				/**
				 * [tFileInputDelimited_5 finally ] start
				 */

				currentComponent = "tFileInputDelimited_5";

				/**
				 * [tFileInputDelimited_5 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
				 */

				/**
				 * [tDBOutput_5 finally ] start
				 */

				currentComponent = "tDBOutput_5";

				if (resourceMap.get("statementClosed_tDBOutput_5") == null) {
					java.sql.PreparedStatement pstmtToClose_tDBOutput_5 = null;
					if ((pstmtToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
							.remove("pstmt_tDBOutput_5")) != null) {
						pstmtToClose_tDBOutput_5.close();
					}
				}

				/**
				 * [tDBOutput_5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", 1);
	}

	public void tDBCommit_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBCommit_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tDBCommit_1 begin ] start
				 */

				ok_Hash.put("tDBCommit_1", false);
				start_Hash.put("tDBCommit_1", System.currentTimeMillis());

				currentComponent = "tDBCommit_1";

				int tos_count_tDBCommit_1 = 0;

				/**
				 * [tDBCommit_1 begin ] stop
				 */

				/**
				 * [tDBCommit_1 main ] start
				 */

				currentComponent = "tDBCommit_1";

				java.sql.Connection conn_tDBCommit_1 = (java.sql.Connection) globalMap.get("conn_tDBConnection_1");
				if (conn_tDBCommit_1 != null && !conn_tDBCommit_1.isClosed()) {

					try {

						conn_tDBCommit_1.commit();

					} finally {

						conn_tDBCommit_1.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_tDBConnection_1"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				tos_count_tDBCommit_1++;

				/**
				 * [tDBCommit_1 main ] stop
				 */

				/**
				 * [tDBCommit_1 process_data_begin ] start
				 */

				currentComponent = "tDBCommit_1";

				/**
				 * [tDBCommit_1 process_data_begin ] stop
				 */

				/**
				 * [tDBCommit_1 process_data_end ] start
				 */

				currentComponent = "tDBCommit_1";

				/**
				 * [tDBCommit_1 process_data_end ] stop
				 */

				/**
				 * [tDBCommit_1 end ] start
				 */

				currentComponent = "tDBCommit_1";

				ok_Hash.put("tDBCommit_1", true);
				end_Hash.put("tDBCommit_1", System.currentTimeMillis());

				/**
				 * [tDBCommit_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBCommit_1 finally ] start
				 */

				currentComponent = "tDBCommit_1";

				/**
				 * [tDBCommit_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBCommit_1_SUBPROCESS_STATE", 1);
	}

	public static class row45Struct implements routines.system.IPersistableComparableLookupRow<row45Struct> {
		final static byte[] commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		static byte[] commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String PREF;

		public String getPREF() {
			return this.PREF;
		}

		public String PDESIG1;

		public String getPDESIG1() {
			return this.PDESIG1;
		}

		public String PDESIG2;

		public String getPDESIG2() {
			return this.PDESIG2;
		}

		public String PDESIG3;

		public String getPDESIG3() {
			return this.PDESIG3;
		}

		public String PPRIX;

		public String getPPRIX() {
			return this.PPRIX;
		}

		public String PCTVA;

		public String getPCTVA() {
			return this.PCTVA;
		}

		public String PCTYV;

		public String getPCTYV() {
			return this.PCTYV;
		}

		public String PCPN;

		public String getPCPN() {
			return this.PCPN;
		}

		public String PPA;

		public String getPPA() {
			return this.PPA;
		}

		public String PQTE;

		public String getPQTE() {
			return this.PQTE;
		}

		public String PFORM01;

		public String getPFORM01() {
			return this.PFORM01;
		}

		public String PFORM02;

		public String getPFORM02() {
			return this.PFORM02;
		}

		public String PNBCAR;

		public String getPNBCAR() {
			return this.PNBCAR;
		}

		public String PVERT;

		public String getPVERT() {
			return this.PVERT;
		}

		public String PEMP2;

		public String getPEMP2() {
			return this.PEMP2;
		}

		public String PLIEU2;

		public String getPLIEU2() {
			return this.PLIEU2;
		}

		public String PREMIMAX;

		public String getPREMIMAX() {
			return this.PREMIMAX;
		}

		public String FILLER2;

		public String getFILLER2() {
			return this.FILLER2;
		}

		public String PMOUV;

		public String getPMOUV() {
			return this.PMOUV;
		}

		public String PTENU;

		public String getPTENU() {
			return this.PTENU;
		}

		public String PINCRE;

		public String getPINCRE() {
			return this.PINCRE;
		}

		public String PPROMPA;

		public String getPPROMPA() {
			return this.PPROMPA;
		}

		public String PACHAT;

		public String getPACHAT() {
			return this.PACHAT;
		}

		public String PARRCDT1;

		public String getPARRCDT1() {
			return this.PARRCDT1;
		}

		public String PARRCDT2;

		public String getPARRCDT2() {
			return this.PARRCDT2;
		}

		public String PECOTAXE;

		public String getPECOTAXE() {
			return this.PECOTAXE;
		}

		public String PPAQUET;

		public String getPPAQUET() {
			return this.PPAQUET;
		}

		public String PEMBAL;

		public String getPEMBAL() {
			return this.PEMBAL;
		}

		public String PGRAMA;

		public String getPGRAMA() {
			return this.PGRAMA;
		}

		public String PDATCRE;

		public String getPDATCRE() {
			return this.PDATCRE;
		}

		public String PDATPRIX;

		public String getPDATPRIX() {
			return this.PDATPRIX;
		}

		public String PDATPA1;

		public String getPDATPA1() {
			return this.PDATPA1;
		}

		public String PPA1;

		public String getPPA1() {
			return this.PPA1;
		}

		public String PPV1;

		public String getPPV1() {
			return this.PPV1;
		}

		public String PPRIXR;

		public String getPPRIXR() {
			return this.PPRIXR;
		}

		public String PPRIXRV1;

		public String getPPRIXRV1() {
			return this.PPRIXRV1;
		}

		public String PPRIXRV2;

		public String getPPRIXRV2() {
			return this.PPRIXRV2;
		}

		public String PPRIXRV3;

		public String getPPRIXRV3() {
			return this.PPRIXRV3;
		}

		public String PPRIREV1;

		public String getPPRIREV1() {
			return this.PPRIREV1;
		}

		public String PPRIREV2;

		public String getPPRIREV2() {
			return this.PPRIREV2;
		}

		public String PPRIREV3;

		public String getPPRIREV3() {
			return this.PPRIREV3;
		}

		public String PDATPV1;

		public String getPDATPV1() {
			return this.PDATPV1;
		}

		public String PMPA;

		public String getPMPA() {
			return this.PMPA;
		}

		public String PETIQ;

		public String getPETIQ() {
			return this.PETIQ;
		}

		public String PFOUR;

		public String getPFOUR() {
			return this.PFOUR;
		}

		public String PPRITTC;

		public String getPPRITTC() {
			return this.PPRITTC;
		}

		public String PIMOD;

		public String getPIMOD() {
			return this.PIMOD;
		}

		public String PPROMREM;

		public String getPPROMREM() {
			return this.PPROMREM;
		}

		public String PUV;

		public String getPUV() {
			return this.PUV;
		}

		public String PUA;

		public String getPUA() {
			return this.PUA;
		}

		public String PDELAI;

		public String getPDELAI() {
			return this.PDELAI;
		}

		public String PMINI;

		public String getPMINI() {
			return this.PMINI;
		}

		public String PMAXI;

		public String getPMAXI() {
			return this.PMAXI;
		}

		public String PCDE;

		public String getPCDE() {
			return this.PCDE;
		}

		public String PEMP;

		public String getPEMP() {
			return this.PEMP;
		}

		public String PPRI1;

		public String getPPRI1() {
			return this.PPRI1;
		}

		public String PPRI2;

		public String getPPRI2() {
			return this.PPRI2;
		}

		public String PPRI3;

		public String getPPRI3() {
			return this.PPRI3;
		}

		public String PPRI4;

		public String getPPRI4() {
			return this.PPRI4;
		}

		public String PPRI5;

		public String getPPRI5() {
			return this.PPRI5;
		}

		public String PPRI6;

		public String getPPRI6() {
			return this.PPRI6;
		}

		public String PREMI1;

		public String getPREMI1() {
			return this.PREMI1;
		}

		public String PREMI2;

		public String getPREMI2() {
			return this.PREMI2;
		}

		public String PREMI3;

		public String getPREMI3() {
			return this.PREMI3;
		}

		public String PREMI4;

		public String getPREMI4() {
			return this.PREMI4;
		}

		public String PREMI5;

		public String getPREMI5() {
			return this.PREMI5;
		}

		public String PREMI6;

		public String getPREMI6() {
			return this.PREMI6;
		}

		public String PQUANT1;

		public String getPQUANT1() {
			return this.PQUANT1;
		}

		public String PQUANT2;

		public String getPQUANT2() {
			return this.PQUANT2;
		}

		public String PQUANT3;

		public String getPQUANT3() {
			return this.PQUANT3;
		}

		public String PQUANT4;

		public String getPQUANT4() {
			return this.PQUANT4;
		}

		public String PQUANT5;

		public String getPQUANT5() {
			return this.PQUANT5;
		}

		public String PQUANT6;

		public String getPQUANT6() {
			return this.PQUANT6;
		}

		public String PEDAT;

		public String getPEDAT() {
			return this.PEDAT;
		}

		public String PSDAT;

		public String getPSDAT() {
			return this.PSDAT;
		}

		public String PRESV;

		public String getPRESV() {
			return this.PRESV;
		}

		public String PREFOU;

		public String getPREFOU() {
			return this.PREFOU;
		}

		public String PGAMEC;

		public String getPGAMEC() {
			return this.PGAMEC;
		}

		public String PGAMER;

		public String getPGAMER() {
			return this.PGAMER;
		}

		public String PGAMEN;

		public String getPGAMEN() {
			return this.PGAMEN;
		}

		public String PTPF;

		public String getPTPF() {
			return this.PTPF;
		}

		public String PSOMMEIL;

		public String getPSOMMEIL() {
			return this.PSOMMEIL;
		}

		public String PINV;

		public String getPINV() {
			return this.PINV;
		}

		public String PMAC;

		public String getPMAC() {
			return this.PMAC;
		}

		public String PREJ;

		public String getPREJ() {
			return this.PREJ;
		}

		public String PMOI;

		public String getPMOI() {
			return this.PMOI;
		}

		public String P1;

		public String getP1() {
			return this.P1;
		}

		public String P2;

		public String getP2() {
			return this.P2;
		}

		public String P3;

		public String getP3() {
			return this.P3;
		}

		public String P4;

		public String getP4() {
			return this.P4;
		}

		public String P5;

		public String getP5() {
			return this.P5;
		}

		public String P6;

		public String getP6() {
			return this.P6;
		}

		public String P7;

		public String getP7() {
			return this.P7;
		}

		public String P8;

		public String getP8() {
			return this.P8;
		}

		public String P9;

		public String getP9() {
			return this.P9;
		}

		public String P10;

		public String getP10() {
			return this.P10;
		}

		public String P11;

		public String getP11() {
			return this.P11;
		}

		public String P12;

		public String getP12() {
			return this.P12;
		}

		public String PAN;

		public String getPAN() {
			return this.PAN;
		}

		public String PAN1;

		public String getPAN1() {
			return this.PAN1;
		}

		public String PAN2;

		public String getPAN2() {
			return this.PAN2;
		}

		public String PPROM;

		public String getPPROM() {
			return this.PPROM;
		}

		public String PPROMD;

		public String getPPROMD() {
			return this.PPROMD;
		}

		public String PCONDI;

		public String getPCONDI() {
			return this.PCONDI;
		}

		public String PRESTE;

		public String getPRESTE() {
			return this.PRESTE;
		}

		public String PTITR;

		public String getPTITR() {
			return this.PTITR;
		}

		public String PPARAG;

		public String getPPARAG() {
			return this.PPARAG;
		}

		public String PPOID;

		public String getPPOID() {
			return this.PPOID;
		}

		public String PKLE;

		public String getPKLE() {
			return this.PKLE;
		}

		public String PLIEN;

		public String getPLIEN() {
			return this.PLIEN;
		}

		public String PSFAM;

		public String getPSFAM() {
			return this.PSFAM;
		}

		public String PREF01;

		public String getPREF01() {
			return this.PREF01;
		}

		public String PREF02;

		public String getPREF02() {
			return this.PREF02;
		}

		public String PREF03;

		public String getPREF03() {
			return this.PREF03;
		}

		public String PTYPE;

		public String getPTYPE() {
			return this.PTYPE;
		}

		public String PLIEU;

		public String getPLIEU() {
			return this.PLIEU;
		}

		public String PCATAL;

		public String getPCATAL() {
			return this.PCATAL;
		}

		public String PUV01;

		public String getPUV01() {
			return this.PUV01;
		}

		public String PCONDI01;

		public String getPCONDI01() {
			return this.PCONDI01;
		}

		public String PUV02;

		public String getPUV02() {
			return this.PUV02;
		}

		public String PCONDI02;

		public String getPCONDI02() {
			return this.PCONDI02;
		}

		public String PPROMDEB;

		public String getPPROMDEB() {
			return this.PPROMDEB;
		}

		public String PREMFOU;

		public String getPREMFOU() {
			return this.PREMFOU;
		}

		public String PQTE01;

		public String getPQTE01() {
			return this.PQTE01;
		}

		public String PQTE02;

		public String getPQTE02() {
			return this.PQTE02;
		}

		public String PRX01;

		public String getPRX01() {
			return this.PRX01;
		}

		public String PRX02;

		public String getPRX02() {
			return this.PRX02;
		}

		public String PCUBAG;

		public String getPCUBAG() {
			return this.PCUBAG;
		}

		public String PREVPOI;

		public String getPREVPOI() {
			return this.PREVPOI;
		}

		public String PREVCUB;

		public String getPREVCUB() {
			return this.PREVCUB;
		}

		public String PREVFAP;

		public String getPREVFAP() {
			return this.PREVFAP;
		}

		public String PINTERNET;

		public String getPINTERNET() {
			return this.PINTERNET;
		}

		public String PFIDEL;

		public String getPFIDEL() {
			return this.PFIDEL;
		}

		public String PCOMMANDE;

		public String getPCOMMANDE() {
			return this.PCOMMANDE;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.PREF == null) ? 0 : this.PREF.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row45Struct other = (row45Struct) obj;

			if (this.PREF == null) {
				if (other.PREF != null)
					return false;

			} else if (!this.PREF.equals(other.PREF))

				return false;

			return true;
		}

		public void copyDataTo(row45Struct other) {

			other.PREF = this.PREF;
			other.PDESIG1 = this.PDESIG1;
			other.PDESIG2 = this.PDESIG2;
			other.PDESIG3 = this.PDESIG3;
			other.PPRIX = this.PPRIX;
			other.PCTVA = this.PCTVA;
			other.PCTYV = this.PCTYV;
			other.PCPN = this.PCPN;
			other.PPA = this.PPA;
			other.PQTE = this.PQTE;
			other.PFORM01 = this.PFORM01;
			other.PFORM02 = this.PFORM02;
			other.PNBCAR = this.PNBCAR;
			other.PVERT = this.PVERT;
			other.PEMP2 = this.PEMP2;
			other.PLIEU2 = this.PLIEU2;
			other.PREMIMAX = this.PREMIMAX;
			other.FILLER2 = this.FILLER2;
			other.PMOUV = this.PMOUV;
			other.PTENU = this.PTENU;
			other.PINCRE = this.PINCRE;
			other.PPROMPA = this.PPROMPA;
			other.PACHAT = this.PACHAT;
			other.PARRCDT1 = this.PARRCDT1;
			other.PARRCDT2 = this.PARRCDT2;
			other.PECOTAXE = this.PECOTAXE;
			other.PPAQUET = this.PPAQUET;
			other.PEMBAL = this.PEMBAL;
			other.PGRAMA = this.PGRAMA;
			other.PDATCRE = this.PDATCRE;
			other.PDATPRIX = this.PDATPRIX;
			other.PDATPA1 = this.PDATPA1;
			other.PPA1 = this.PPA1;
			other.PPV1 = this.PPV1;
			other.PPRIXR = this.PPRIXR;
			other.PPRIXRV1 = this.PPRIXRV1;
			other.PPRIXRV2 = this.PPRIXRV2;
			other.PPRIXRV3 = this.PPRIXRV3;
			other.PPRIREV1 = this.PPRIREV1;
			other.PPRIREV2 = this.PPRIREV2;
			other.PPRIREV3 = this.PPRIREV3;
			other.PDATPV1 = this.PDATPV1;
			other.PMPA = this.PMPA;
			other.PETIQ = this.PETIQ;
			other.PFOUR = this.PFOUR;
			other.PPRITTC = this.PPRITTC;
			other.PIMOD = this.PIMOD;
			other.PPROMREM = this.PPROMREM;
			other.PUV = this.PUV;
			other.PUA = this.PUA;
			other.PDELAI = this.PDELAI;
			other.PMINI = this.PMINI;
			other.PMAXI = this.PMAXI;
			other.PCDE = this.PCDE;
			other.PEMP = this.PEMP;
			other.PPRI1 = this.PPRI1;
			other.PPRI2 = this.PPRI2;
			other.PPRI3 = this.PPRI3;
			other.PPRI4 = this.PPRI4;
			other.PPRI5 = this.PPRI5;
			other.PPRI6 = this.PPRI6;
			other.PREMI1 = this.PREMI1;
			other.PREMI2 = this.PREMI2;
			other.PREMI3 = this.PREMI3;
			other.PREMI4 = this.PREMI4;
			other.PREMI5 = this.PREMI5;
			other.PREMI6 = this.PREMI6;
			other.PQUANT1 = this.PQUANT1;
			other.PQUANT2 = this.PQUANT2;
			other.PQUANT3 = this.PQUANT3;
			other.PQUANT4 = this.PQUANT4;
			other.PQUANT5 = this.PQUANT5;
			other.PQUANT6 = this.PQUANT6;
			other.PEDAT = this.PEDAT;
			other.PSDAT = this.PSDAT;
			other.PRESV = this.PRESV;
			other.PREFOU = this.PREFOU;
			other.PGAMEC = this.PGAMEC;
			other.PGAMER = this.PGAMER;
			other.PGAMEN = this.PGAMEN;
			other.PTPF = this.PTPF;
			other.PSOMMEIL = this.PSOMMEIL;
			other.PINV = this.PINV;
			other.PMAC = this.PMAC;
			other.PREJ = this.PREJ;
			other.PMOI = this.PMOI;
			other.P1 = this.P1;
			other.P2 = this.P2;
			other.P3 = this.P3;
			other.P4 = this.P4;
			other.P5 = this.P5;
			other.P6 = this.P6;
			other.P7 = this.P7;
			other.P8 = this.P8;
			other.P9 = this.P9;
			other.P10 = this.P10;
			other.P11 = this.P11;
			other.P12 = this.P12;
			other.PAN = this.PAN;
			other.PAN1 = this.PAN1;
			other.PAN2 = this.PAN2;
			other.PPROM = this.PPROM;
			other.PPROMD = this.PPROMD;
			other.PCONDI = this.PCONDI;
			other.PRESTE = this.PRESTE;
			other.PTITR = this.PTITR;
			other.PPARAG = this.PPARAG;
			other.PPOID = this.PPOID;
			other.PKLE = this.PKLE;
			other.PLIEN = this.PLIEN;
			other.PSFAM = this.PSFAM;
			other.PREF01 = this.PREF01;
			other.PREF02 = this.PREF02;
			other.PREF03 = this.PREF03;
			other.PTYPE = this.PTYPE;
			other.PLIEU = this.PLIEU;
			other.PCATAL = this.PCATAL;
			other.PUV01 = this.PUV01;
			other.PCONDI01 = this.PCONDI01;
			other.PUV02 = this.PUV02;
			other.PCONDI02 = this.PCONDI02;
			other.PPROMDEB = this.PPROMDEB;
			other.PREMFOU = this.PREMFOU;
			other.PQTE01 = this.PQTE01;
			other.PQTE02 = this.PQTE02;
			other.PRX01 = this.PRX01;
			other.PRX02 = this.PRX02;
			other.PCUBAG = this.PCUBAG;
			other.PREVPOI = this.PREVPOI;
			other.PREVCUB = this.PREVCUB;
			other.PREVFAP = this.PREVFAP;
			other.PINTERNET = this.PINTERNET;
			other.PFIDEL = this.PFIDEL;
			other.PCOMMANDE = this.PCOMMANDE;

		}

		public void copyKeysDataTo(row45Struct other) {

			other.PREF = this.PREF;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length) {
					if (length < 1024 && commonByteArray_TALEND_MONTAUBAN_FinalJobPart2.length == 0) {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[1024];
					} else {
						commonByteArray_TALEND_MONTAUBAN_FinalJobPart2 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length);
				strReturn = new String(commonByteArray_TALEND_MONTAUBAN_FinalJobPart2, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.PREF = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_MONTAUBAN_FinalJobPart2) {

				try {

					int length = 0;

					this.PREF = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.PREF, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.PREF, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.PDESIG1 = readString(dis, ois);

				this.PDESIG2 = readString(dis, ois);

				this.PDESIG3 = readString(dis, ois);

				this.PPRIX = readString(dis, ois);

				this.PCTVA = readString(dis, ois);

				this.PCTYV = readString(dis, ois);

				this.PCPN = readString(dis, ois);

				this.PPA = readString(dis, ois);

				this.PQTE = readString(dis, ois);

				this.PFORM01 = readString(dis, ois);

				this.PFORM02 = readString(dis, ois);

				this.PNBCAR = readString(dis, ois);

				this.PVERT = readString(dis, ois);

				this.PEMP2 = readString(dis, ois);

				this.PLIEU2 = readString(dis, ois);

				this.PREMIMAX = readString(dis, ois);

				this.FILLER2 = readString(dis, ois);

				this.PMOUV = readString(dis, ois);

				this.PTENU = readString(dis, ois);

				this.PINCRE = readString(dis, ois);

				this.PPROMPA = readString(dis, ois);

				this.PACHAT = readString(dis, ois);

				this.PARRCDT1 = readString(dis, ois);

				this.PARRCDT2 = readString(dis, ois);

				this.PECOTAXE = readString(dis, ois);

				this.PPAQUET = readString(dis, ois);

				this.PEMBAL = readString(dis, ois);

				this.PGRAMA = readString(dis, ois);

				this.PDATCRE = readString(dis, ois);

				this.PDATPRIX = readString(dis, ois);

				this.PDATPA1 = readString(dis, ois);

				this.PPA1 = readString(dis, ois);

				this.PPV1 = readString(dis, ois);

				this.PPRIXR = readString(dis, ois);

				this.PPRIXRV1 = readString(dis, ois);

				this.PPRIXRV2 = readString(dis, ois);

				this.PPRIXRV3 = readString(dis, ois);

				this.PPRIREV1 = readString(dis, ois);

				this.PPRIREV2 = readString(dis, ois);

				this.PPRIREV3 = readString(dis, ois);

				this.PDATPV1 = readString(dis, ois);

				this.PMPA = readString(dis, ois);

				this.PETIQ = readString(dis, ois);

				this.PFOUR = readString(dis, ois);

				this.PPRITTC = readString(dis, ois);

				this.PIMOD = readString(dis, ois);

				this.PPROMREM = readString(dis, ois);

				this.PUV = readString(dis, ois);

				this.PUA = readString(dis, ois);

				this.PDELAI = readString(dis, ois);

				this.PMINI = readString(dis, ois);

				this.PMAXI = readString(dis, ois);

				this.PCDE = readString(dis, ois);

				this.PEMP = readString(dis, ois);

				this.PPRI1 = readString(dis, ois);

				this.PPRI2 = readString(dis, ois);

				this.PPRI3 = readString(dis, ois);

				this.PPRI4 = readString(dis, ois);

				this.PPRI5 = readString(dis, ois);

				this.PPRI6 = readString(dis, ois);

				this.PREMI1 = readString(dis, ois);

				this.PREMI2 = readString(dis, ois);

				this.PREMI3 = readString(dis, ois);

				this.PREMI4 = readString(dis, ois);

				this.PREMI5 = readString(dis, ois);

				this.PREMI6 = readString(dis, ois);

				this.PQUANT1 = readString(dis, ois);

				this.PQUANT2 = readString(dis, ois);

				this.PQUANT3 = readString(dis, ois);

				this.PQUANT4 = readString(dis, ois);

				this.PQUANT5 = readString(dis, ois);

				this.PQUANT6 = readString(dis, ois);

				this.PEDAT = readString(dis, ois);

				this.PSDAT = readString(dis, ois);

				this.PRESV = readString(dis, ois);

				this.PREFOU = readString(dis, ois);

				this.PGAMEC = readString(dis, ois);

				this.PGAMER = readString(dis, ois);

				this.PGAMEN = readString(dis, ois);

				this.PTPF = readString(dis, ois);

				this.PSOMMEIL = readString(dis, ois);

				this.PINV = readString(dis, ois);

				this.PMAC = readString(dis, ois);

				this.PREJ = readString(dis, ois);

				this.PMOI = readString(dis, ois);

				this.P1 = readString(dis, ois);

				this.P2 = readString(dis, ois);

				this.P3 = readString(dis, ois);

				this.P4 = readString(dis, ois);

				this.P5 = readString(dis, ois);

				this.P6 = readString(dis, ois);

				this.P7 = readString(dis, ois);

				this.P8 = readString(dis, ois);

				this.P9 = readString(dis, ois);

				this.P10 = readString(dis, ois);

				this.P11 = readString(dis, ois);

				this.P12 = readString(dis, ois);

				this.PAN = readString(dis, ois);

				this.PAN1 = readString(dis, ois);

				this.PAN2 = readString(dis, ois);

				this.PPROM = readString(dis, ois);

				this.PPROMD = readString(dis, ois);

				this.PCONDI = readString(dis, ois);

				this.PRESTE = readString(dis, ois);

				this.PTITR = readString(dis, ois);

				this.PPARAG = readString(dis, ois);

				this.PPOID = readString(dis, ois);

				this.PKLE = readString(dis, ois);

				this.PLIEN = readString(dis, ois);

				this.PSFAM = readString(dis, ois);

				this.PREF01 = readString(dis, ois);

				this.PREF02 = readString(dis, ois);

				this.PREF03 = readString(dis, ois);

				this.PTYPE = readString(dis, ois);

				this.PLIEU = readString(dis, ois);

				this.PCATAL = readString(dis, ois);

				this.PUV01 = readString(dis, ois);

				this.PCONDI01 = readString(dis, ois);

				this.PUV02 = readString(dis, ois);

				this.PCONDI02 = readString(dis, ois);

				this.PPROMDEB = readString(dis, ois);

				this.PREMFOU = readString(dis, ois);

				this.PQTE01 = readString(dis, ois);

				this.PQTE02 = readString(dis, ois);

				this.PRX01 = readString(dis, ois);

				this.PRX02 = readString(dis, ois);

				this.PCUBAG = readString(dis, ois);

				this.PREVPOI = readString(dis, ois);

				this.PREVCUB = readString(dis, ois);

				this.PREVFAP = readString(dis, ois);

				this.PINTERNET = readString(dis, ois);

				this.PFIDEL = readString(dis, ois);

				this.PCOMMANDE = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.PDESIG1 = readString(dis, objectIn);

				this.PDESIG2 = readString(dis, objectIn);

				this.PDESIG3 = readString(dis, objectIn);

				this.PPRIX = readString(dis, objectIn);

				this.PCTVA = readString(dis, objectIn);

				this.PCTYV = readString(dis, objectIn);

				this.PCPN = readString(dis, objectIn);

				this.PPA = readString(dis, objectIn);

				this.PQTE = readString(dis, objectIn);

				this.PFORM01 = readString(dis, objectIn);

				this.PFORM02 = readString(dis, objectIn);

				this.PNBCAR = readString(dis, objectIn);

				this.PVERT = readString(dis, objectIn);

				this.PEMP2 = readString(dis, objectIn);

				this.PLIEU2 = readString(dis, objectIn);

				this.PREMIMAX = readString(dis, objectIn);

				this.FILLER2 = readString(dis, objectIn);

				this.PMOUV = readString(dis, objectIn);

				this.PTENU = readString(dis, objectIn);

				this.PINCRE = readString(dis, objectIn);

				this.PPROMPA = readString(dis, objectIn);

				this.PACHAT = readString(dis, objectIn);

				this.PARRCDT1 = readString(dis, objectIn);

				this.PARRCDT2 = readString(dis, objectIn);

				this.PECOTAXE = readString(dis, objectIn);

				this.PPAQUET = readString(dis, objectIn);

				this.PEMBAL = readString(dis, objectIn);

				this.PGRAMA = readString(dis, objectIn);

				this.PDATCRE = readString(dis, objectIn);

				this.PDATPRIX = readString(dis, objectIn);

				this.PDATPA1 = readString(dis, objectIn);

				this.PPA1 = readString(dis, objectIn);

				this.PPV1 = readString(dis, objectIn);

				this.PPRIXR = readString(dis, objectIn);

				this.PPRIXRV1 = readString(dis, objectIn);

				this.PPRIXRV2 = readString(dis, objectIn);

				this.PPRIXRV3 = readString(dis, objectIn);

				this.PPRIREV1 = readString(dis, objectIn);

				this.PPRIREV2 = readString(dis, objectIn);

				this.PPRIREV3 = readString(dis, objectIn);

				this.PDATPV1 = readString(dis, objectIn);

				this.PMPA = readString(dis, objectIn);

				this.PETIQ = readString(dis, objectIn);

				this.PFOUR = readString(dis, objectIn);

				this.PPRITTC = readString(dis, objectIn);

				this.PIMOD = readString(dis, objectIn);

				this.PPROMREM = readString(dis, objectIn);

				this.PUV = readString(dis, objectIn);

				this.PUA = readString(dis, objectIn);

				this.PDELAI = readString(dis, objectIn);

				this.PMINI = readString(dis, objectIn);

				this.PMAXI = readString(dis, objectIn);

				this.PCDE = readString(dis, objectIn);

				this.PEMP = readString(dis, objectIn);

				this.PPRI1 = readString(dis, objectIn);

				this.PPRI2 = readString(dis, objectIn);

				this.PPRI3 = readString(dis, objectIn);

				this.PPRI4 = readString(dis, objectIn);

				this.PPRI5 = readString(dis, objectIn);

				this.PPRI6 = readString(dis, objectIn);

				this.PREMI1 = readString(dis, objectIn);

				this.PREMI2 = readString(dis, objectIn);

				this.PREMI3 = readString(dis, objectIn);

				this.PREMI4 = readString(dis, objectIn);

				this.PREMI5 = readString(dis, objectIn);

				this.PREMI6 = readString(dis, objectIn);

				this.PQUANT1 = readString(dis, objectIn);

				this.PQUANT2 = readString(dis, objectIn);

				this.PQUANT3 = readString(dis, objectIn);

				this.PQUANT4 = readString(dis, objectIn);

				this.PQUANT5 = readString(dis, objectIn);

				this.PQUANT6 = readString(dis, objectIn);

				this.PEDAT = readString(dis, objectIn);

				this.PSDAT = readString(dis, objectIn);

				this.PRESV = readString(dis, objectIn);

				this.PREFOU = readString(dis, objectIn);

				this.PGAMEC = readString(dis, objectIn);

				this.PGAMER = readString(dis, objectIn);

				this.PGAMEN = readString(dis, objectIn);

				this.PTPF = readString(dis, objectIn);

				this.PSOMMEIL = readString(dis, objectIn);

				this.PINV = readString(dis, objectIn);

				this.PMAC = readString(dis, objectIn);

				this.PREJ = readString(dis, objectIn);

				this.PMOI = readString(dis, objectIn);

				this.P1 = readString(dis, objectIn);

				this.P2 = readString(dis, objectIn);

				this.P3 = readString(dis, objectIn);

				this.P4 = readString(dis, objectIn);

				this.P5 = readString(dis, objectIn);

				this.P6 = readString(dis, objectIn);

				this.P7 = readString(dis, objectIn);

				this.P8 = readString(dis, objectIn);

				this.P9 = readString(dis, objectIn);

				this.P10 = readString(dis, objectIn);

				this.P11 = readString(dis, objectIn);

				this.P12 = readString(dis, objectIn);

				this.PAN = readString(dis, objectIn);

				this.PAN1 = readString(dis, objectIn);

				this.PAN2 = readString(dis, objectIn);

				this.PPROM = readString(dis, objectIn);

				this.PPROMD = readString(dis, objectIn);

				this.PCONDI = readString(dis, objectIn);

				this.PRESTE = readString(dis, objectIn);

				this.PTITR = readString(dis, objectIn);

				this.PPARAG = readString(dis, objectIn);

				this.PPOID = readString(dis, objectIn);

				this.PKLE = readString(dis, objectIn);

				this.PLIEN = readString(dis, objectIn);

				this.PSFAM = readString(dis, objectIn);

				this.PREF01 = readString(dis, objectIn);

				this.PREF02 = readString(dis, objectIn);

				this.PREF03 = readString(dis, objectIn);

				this.PTYPE = readString(dis, objectIn);

				this.PLIEU = readString(dis, objectIn);

				this.PCATAL = readString(dis, objectIn);

				this.PUV01 = readString(dis, objectIn);

				this.PCONDI01 = readString(dis, objectIn);

				this.PUV02 = readString(dis, objectIn);

				this.PCONDI02 = readString(dis, objectIn);

				this.PPROMDEB = readString(dis, objectIn);

				this.PREMFOU = readString(dis, objectIn);

				this.PQTE01 = readString(dis, objectIn);

				this.PQTE02 = readString(dis, objectIn);

				this.PRX01 = readString(dis, objectIn);

				this.PRX02 = readString(dis, objectIn);

				this.PCUBAG = readString(dis, objectIn);

				this.PREVPOI = readString(dis, objectIn);

				this.PREVCUB = readString(dis, objectIn);

				this.PREVFAP = readString(dis, objectIn);

				this.PINTERNET = readString(dis, objectIn);

				this.PFIDEL = readString(dis, objectIn);

				this.PCOMMANDE = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.PDESIG1, dos, oos);

				writeString(this.PDESIG2, dos, oos);

				writeString(this.PDESIG3, dos, oos);

				writeString(this.PPRIX, dos, oos);

				writeString(this.PCTVA, dos, oos);

				writeString(this.PCTYV, dos, oos);

				writeString(this.PCPN, dos, oos);

				writeString(this.PPA, dos, oos);

				writeString(this.PQTE, dos, oos);

				writeString(this.PFORM01, dos, oos);

				writeString(this.PFORM02, dos, oos);

				writeString(this.PNBCAR, dos, oos);

				writeString(this.PVERT, dos, oos);

				writeString(this.PEMP2, dos, oos);

				writeString(this.PLIEU2, dos, oos);

				writeString(this.PREMIMAX, dos, oos);

				writeString(this.FILLER2, dos, oos);

				writeString(this.PMOUV, dos, oos);

				writeString(this.PTENU, dos, oos);

				writeString(this.PINCRE, dos, oos);

				writeString(this.PPROMPA, dos, oos);

				writeString(this.PACHAT, dos, oos);

				writeString(this.PARRCDT1, dos, oos);

				writeString(this.PARRCDT2, dos, oos);

				writeString(this.PECOTAXE, dos, oos);

				writeString(this.PPAQUET, dos, oos);

				writeString(this.PEMBAL, dos, oos);

				writeString(this.PGRAMA, dos, oos);

				writeString(this.PDATCRE, dos, oos);

				writeString(this.PDATPRIX, dos, oos);

				writeString(this.PDATPA1, dos, oos);

				writeString(this.PPA1, dos, oos);

				writeString(this.PPV1, dos, oos);

				writeString(this.PPRIXR, dos, oos);

				writeString(this.PPRIXRV1, dos, oos);

				writeString(this.PPRIXRV2, dos, oos);

				writeString(this.PPRIXRV3, dos, oos);

				writeString(this.PPRIREV1, dos, oos);

				writeString(this.PPRIREV2, dos, oos);

				writeString(this.PPRIREV3, dos, oos);

				writeString(this.PDATPV1, dos, oos);

				writeString(this.PMPA, dos, oos);

				writeString(this.PETIQ, dos, oos);

				writeString(this.PFOUR, dos, oos);

				writeString(this.PPRITTC, dos, oos);

				writeString(this.PIMOD, dos, oos);

				writeString(this.PPROMREM, dos, oos);

				writeString(this.PUV, dos, oos);

				writeString(this.PUA, dos, oos);

				writeString(this.PDELAI, dos, oos);

				writeString(this.PMINI, dos, oos);

				writeString(this.PMAXI, dos, oos);

				writeString(this.PCDE, dos, oos);

				writeString(this.PEMP, dos, oos);

				writeString(this.PPRI1, dos, oos);

				writeString(this.PPRI2, dos, oos);

				writeString(this.PPRI3, dos, oos);

				writeString(this.PPRI4, dos, oos);

				writeString(this.PPRI5, dos, oos);

				writeString(this.PPRI6, dos, oos);

				writeString(this.PREMI1, dos, oos);

				writeString(this.PREMI2, dos, oos);

				writeString(this.PREMI3, dos, oos);

				writeString(this.PREMI4, dos, oos);

				writeString(this.PREMI5, dos, oos);

				writeString(this.PREMI6, dos, oos);

				writeString(this.PQUANT1, dos, oos);

				writeString(this.PQUANT2, dos, oos);

				writeString(this.PQUANT3, dos, oos);

				writeString(this.PQUANT4, dos, oos);

				writeString(this.PQUANT5, dos, oos);

				writeString(this.PQUANT6, dos, oos);

				writeString(this.PEDAT, dos, oos);

				writeString(this.PSDAT, dos, oos);

				writeString(this.PRESV, dos, oos);

				writeString(this.PREFOU, dos, oos);

				writeString(this.PGAMEC, dos, oos);

				writeString(this.PGAMER, dos, oos);

				writeString(this.PGAMEN, dos, oos);

				writeString(this.PTPF, dos, oos);

				writeString(this.PSOMMEIL, dos, oos);

				writeString(this.PINV, dos, oos);

				writeString(this.PMAC, dos, oos);

				writeString(this.PREJ, dos, oos);

				writeString(this.PMOI, dos, oos);

				writeString(this.P1, dos, oos);

				writeString(this.P2, dos, oos);

				writeString(this.P3, dos, oos);

				writeString(this.P4, dos, oos);

				writeString(this.P5, dos, oos);

				writeString(this.P6, dos, oos);

				writeString(this.P7, dos, oos);

				writeString(this.P8, dos, oos);

				writeString(this.P9, dos, oos);

				writeString(this.P10, dos, oos);

				writeString(this.P11, dos, oos);

				writeString(this.P12, dos, oos);

				writeString(this.PAN, dos, oos);

				writeString(this.PAN1, dos, oos);

				writeString(this.PAN2, dos, oos);

				writeString(this.PPROM, dos, oos);

				writeString(this.PPROMD, dos, oos);

				writeString(this.PCONDI, dos, oos);

				writeString(this.PRESTE, dos, oos);

				writeString(this.PTITR, dos, oos);

				writeString(this.PPARAG, dos, oos);

				writeString(this.PPOID, dos, oos);

				writeString(this.PKLE, dos, oos);

				writeString(this.PLIEN, dos, oos);

				writeString(this.PSFAM, dos, oos);

				writeString(this.PREF01, dos, oos);

				writeString(this.PREF02, dos, oos);

				writeString(this.PREF03, dos, oos);

				writeString(this.PTYPE, dos, oos);

				writeString(this.PLIEU, dos, oos);

				writeString(this.PCATAL, dos, oos);

				writeString(this.PUV01, dos, oos);

				writeString(this.PCONDI01, dos, oos);

				writeString(this.PUV02, dos, oos);

				writeString(this.PCONDI02, dos, oos);

				writeString(this.PPROMDEB, dos, oos);

				writeString(this.PREMFOU, dos, oos);

				writeString(this.PQTE01, dos, oos);

				writeString(this.PQTE02, dos, oos);

				writeString(this.PRX01, dos, oos);

				writeString(this.PRX02, dos, oos);

				writeString(this.PCUBAG, dos, oos);

				writeString(this.PREVPOI, dos, oos);

				writeString(this.PREVCUB, dos, oos);

				writeString(this.PREVFAP, dos, oos);

				writeString(this.PINTERNET, dos, oos);

				writeString(this.PFIDEL, dos, oos);

				writeString(this.PCOMMANDE, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.PDESIG1, dos, objectOut);

				writeString(this.PDESIG2, dos, objectOut);

				writeString(this.PDESIG3, dos, objectOut);

				writeString(this.PPRIX, dos, objectOut);

				writeString(this.PCTVA, dos, objectOut);

				writeString(this.PCTYV, dos, objectOut);

				writeString(this.PCPN, dos, objectOut);

				writeString(this.PPA, dos, objectOut);

				writeString(this.PQTE, dos, objectOut);

				writeString(this.PFORM01, dos, objectOut);

				writeString(this.PFORM02, dos, objectOut);

				writeString(this.PNBCAR, dos, objectOut);

				writeString(this.PVERT, dos, objectOut);

				writeString(this.PEMP2, dos, objectOut);

				writeString(this.PLIEU2, dos, objectOut);

				writeString(this.PREMIMAX, dos, objectOut);

				writeString(this.FILLER2, dos, objectOut);

				writeString(this.PMOUV, dos, objectOut);

				writeString(this.PTENU, dos, objectOut);

				writeString(this.PINCRE, dos, objectOut);

				writeString(this.PPROMPA, dos, objectOut);

				writeString(this.PACHAT, dos, objectOut);

				writeString(this.PARRCDT1, dos, objectOut);

				writeString(this.PARRCDT2, dos, objectOut);

				writeString(this.PECOTAXE, dos, objectOut);

				writeString(this.PPAQUET, dos, objectOut);

				writeString(this.PEMBAL, dos, objectOut);

				writeString(this.PGRAMA, dos, objectOut);

				writeString(this.PDATCRE, dos, objectOut);

				writeString(this.PDATPRIX, dos, objectOut);

				writeString(this.PDATPA1, dos, objectOut);

				writeString(this.PPA1, dos, objectOut);

				writeString(this.PPV1, dos, objectOut);

				writeString(this.PPRIXR, dos, objectOut);

				writeString(this.PPRIXRV1, dos, objectOut);

				writeString(this.PPRIXRV2, dos, objectOut);

				writeString(this.PPRIXRV3, dos, objectOut);

				writeString(this.PPRIREV1, dos, objectOut);

				writeString(this.PPRIREV2, dos, objectOut);

				writeString(this.PPRIREV3, dos, objectOut);

				writeString(this.PDATPV1, dos, objectOut);

				writeString(this.PMPA, dos, objectOut);

				writeString(this.PETIQ, dos, objectOut);

				writeString(this.PFOUR, dos, objectOut);

				writeString(this.PPRITTC, dos, objectOut);

				writeString(this.PIMOD, dos, objectOut);

				writeString(this.PPROMREM, dos, objectOut);

				writeString(this.PUV, dos, objectOut);

				writeString(this.PUA, dos, objectOut);

				writeString(this.PDELAI, dos, objectOut);

				writeString(this.PMINI, dos, objectOut);

				writeString(this.PMAXI, dos, objectOut);

				writeString(this.PCDE, dos, objectOut);

				writeString(this.PEMP, dos, objectOut);

				writeString(this.PPRI1, dos, objectOut);

				writeString(this.PPRI2, dos, objectOut);

				writeString(this.PPRI3, dos, objectOut);

				writeString(this.PPRI4, dos, objectOut);

				writeString(this.PPRI5, dos, objectOut);

				writeString(this.PPRI6, dos, objectOut);

				writeString(this.PREMI1, dos, objectOut);

				writeString(this.PREMI2, dos, objectOut);

				writeString(this.PREMI3, dos, objectOut);

				writeString(this.PREMI4, dos, objectOut);

				writeString(this.PREMI5, dos, objectOut);

				writeString(this.PREMI6, dos, objectOut);

				writeString(this.PQUANT1, dos, objectOut);

				writeString(this.PQUANT2, dos, objectOut);

				writeString(this.PQUANT3, dos, objectOut);

				writeString(this.PQUANT4, dos, objectOut);

				writeString(this.PQUANT5, dos, objectOut);

				writeString(this.PQUANT6, dos, objectOut);

				writeString(this.PEDAT, dos, objectOut);

				writeString(this.PSDAT, dos, objectOut);

				writeString(this.PRESV, dos, objectOut);

				writeString(this.PREFOU, dos, objectOut);

				writeString(this.PGAMEC, dos, objectOut);

				writeString(this.PGAMER, dos, objectOut);

				writeString(this.PGAMEN, dos, objectOut);

				writeString(this.PTPF, dos, objectOut);

				writeString(this.PSOMMEIL, dos, objectOut);

				writeString(this.PINV, dos, objectOut);

				writeString(this.PMAC, dos, objectOut);

				writeString(this.PREJ, dos, objectOut);

				writeString(this.PMOI, dos, objectOut);

				writeString(this.P1, dos, objectOut);

				writeString(this.P2, dos, objectOut);

				writeString(this.P3, dos, objectOut);

				writeString(this.P4, dos, objectOut);

				writeString(this.P5, dos, objectOut);

				writeString(this.P6, dos, objectOut);

				writeString(this.P7, dos, objectOut);

				writeString(this.P8, dos, objectOut);

				writeString(this.P9, dos, objectOut);

				writeString(this.P10, dos, objectOut);

				writeString(this.P11, dos, objectOut);

				writeString(this.P12, dos, objectOut);

				writeString(this.PAN, dos, objectOut);

				writeString(this.PAN1, dos, objectOut);

				writeString(this.PAN2, dos, objectOut);

				writeString(this.PPROM, dos, objectOut);

				writeString(this.PPROMD, dos, objectOut);

				writeString(this.PCONDI, dos, objectOut);

				writeString(this.PRESTE, dos, objectOut);

				writeString(this.PTITR, dos, objectOut);

				writeString(this.PPARAG, dos, objectOut);

				writeString(this.PPOID, dos, objectOut);

				writeString(this.PKLE, dos, objectOut);

				writeString(this.PLIEN, dos, objectOut);

				writeString(this.PSFAM, dos, objectOut);

				writeString(this.PREF01, dos, objectOut);

				writeString(this.PREF02, dos, objectOut);

				writeString(this.PREF03, dos, objectOut);

				writeString(this.PTYPE, dos, objectOut);

				writeString(this.PLIEU, dos, objectOut);

				writeString(this.PCATAL, dos, objectOut);

				writeString(this.PUV01, dos, objectOut);

				writeString(this.PCONDI01, dos, objectOut);

				writeString(this.PUV02, dos, objectOut);

				writeString(this.PCONDI02, dos, objectOut);

				writeString(this.PPROMDEB, dos, objectOut);

				writeString(this.PREMFOU, dos, objectOut);

				writeString(this.PQTE01, dos, objectOut);

				writeString(this.PQTE02, dos, objectOut);

				writeString(this.PRX01, dos, objectOut);

				writeString(this.PRX02, dos, objectOut);

				writeString(this.PCUBAG, dos, objectOut);

				writeString(this.PREVPOI, dos, objectOut);

				writeString(this.PREVCUB, dos, objectOut);

				writeString(this.PREVFAP, dos, objectOut);

				writeString(this.PINTERNET, dos, objectOut);

				writeString(this.PFIDEL, dos, objectOut);

				writeString(this.PCOMMANDE, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PREF=" + PREF);
			sb.append(",PDESIG1=" + PDESIG1);
			sb.append(",PDESIG2=" + PDESIG2);
			sb.append(",PDESIG3=" + PDESIG3);
			sb.append(",PPRIX=" + PPRIX);
			sb.append(",PCTVA=" + PCTVA);
			sb.append(",PCTYV=" + PCTYV);
			sb.append(",PCPN=" + PCPN);
			sb.append(",PPA=" + PPA);
			sb.append(",PQTE=" + PQTE);
			sb.append(",PFORM01=" + PFORM01);
			sb.append(",PFORM02=" + PFORM02);
			sb.append(",PNBCAR=" + PNBCAR);
			sb.append(",PVERT=" + PVERT);
			sb.append(",PEMP2=" + PEMP2);
			sb.append(",PLIEU2=" + PLIEU2);
			sb.append(",PREMIMAX=" + PREMIMAX);
			sb.append(",FILLER2=" + FILLER2);
			sb.append(",PMOUV=" + PMOUV);
			sb.append(",PTENU=" + PTENU);
			sb.append(",PINCRE=" + PINCRE);
			sb.append(",PPROMPA=" + PPROMPA);
			sb.append(",PACHAT=" + PACHAT);
			sb.append(",PARRCDT1=" + PARRCDT1);
			sb.append(",PARRCDT2=" + PARRCDT2);
			sb.append(",PECOTAXE=" + PECOTAXE);
			sb.append(",PPAQUET=" + PPAQUET);
			sb.append(",PEMBAL=" + PEMBAL);
			sb.append(",PGRAMA=" + PGRAMA);
			sb.append(",PDATCRE=" + PDATCRE);
			sb.append(",PDATPRIX=" + PDATPRIX);
			sb.append(",PDATPA1=" + PDATPA1);
			sb.append(",PPA1=" + PPA1);
			sb.append(",PPV1=" + PPV1);
			sb.append(",PPRIXR=" + PPRIXR);
			sb.append(",PPRIXRV1=" + PPRIXRV1);
			sb.append(",PPRIXRV2=" + PPRIXRV2);
			sb.append(",PPRIXRV3=" + PPRIXRV3);
			sb.append(",PPRIREV1=" + PPRIREV1);
			sb.append(",PPRIREV2=" + PPRIREV2);
			sb.append(",PPRIREV3=" + PPRIREV3);
			sb.append(",PDATPV1=" + PDATPV1);
			sb.append(",PMPA=" + PMPA);
			sb.append(",PETIQ=" + PETIQ);
			sb.append(",PFOUR=" + PFOUR);
			sb.append(",PPRITTC=" + PPRITTC);
			sb.append(",PIMOD=" + PIMOD);
			sb.append(",PPROMREM=" + PPROMREM);
			sb.append(",PUV=" + PUV);
			sb.append(",PUA=" + PUA);
			sb.append(",PDELAI=" + PDELAI);
			sb.append(",PMINI=" + PMINI);
			sb.append(",PMAXI=" + PMAXI);
			sb.append(",PCDE=" + PCDE);
			sb.append(",PEMP=" + PEMP);
			sb.append(",PPRI1=" + PPRI1);
			sb.append(",PPRI2=" + PPRI2);
			sb.append(",PPRI3=" + PPRI3);
			sb.append(",PPRI4=" + PPRI4);
			sb.append(",PPRI5=" + PPRI5);
			sb.append(",PPRI6=" + PPRI6);
			sb.append(",PREMI1=" + PREMI1);
			sb.append(",PREMI2=" + PREMI2);
			sb.append(",PREMI3=" + PREMI3);
			sb.append(",PREMI4=" + PREMI4);
			sb.append(",PREMI5=" + PREMI5);
			sb.append(",PREMI6=" + PREMI6);
			sb.append(",PQUANT1=" + PQUANT1);
			sb.append(",PQUANT2=" + PQUANT2);
			sb.append(",PQUANT3=" + PQUANT3);
			sb.append(",PQUANT4=" + PQUANT4);
			sb.append(",PQUANT5=" + PQUANT5);
			sb.append(",PQUANT6=" + PQUANT6);
			sb.append(",PEDAT=" + PEDAT);
			sb.append(",PSDAT=" + PSDAT);
			sb.append(",PRESV=" + PRESV);
			sb.append(",PREFOU=" + PREFOU);
			sb.append(",PGAMEC=" + PGAMEC);
			sb.append(",PGAMER=" + PGAMER);
			sb.append(",PGAMEN=" + PGAMEN);
			sb.append(",PTPF=" + PTPF);
			sb.append(",PSOMMEIL=" + PSOMMEIL);
			sb.append(",PINV=" + PINV);
			sb.append(",PMAC=" + PMAC);
			sb.append(",PREJ=" + PREJ);
			sb.append(",PMOI=" + PMOI);
			sb.append(",P1=" + P1);
			sb.append(",P2=" + P2);
			sb.append(",P3=" + P3);
			sb.append(",P4=" + P4);
			sb.append(",P5=" + P5);
			sb.append(",P6=" + P6);
			sb.append(",P7=" + P7);
			sb.append(",P8=" + P8);
			sb.append(",P9=" + P9);
			sb.append(",P10=" + P10);
			sb.append(",P11=" + P11);
			sb.append(",P12=" + P12);
			sb.append(",PAN=" + PAN);
			sb.append(",PAN1=" + PAN1);
			sb.append(",PAN2=" + PAN2);
			sb.append(",PPROM=" + PPROM);
			sb.append(",PPROMD=" + PPROMD);
			sb.append(",PCONDI=" + PCONDI);
			sb.append(",PRESTE=" + PRESTE);
			sb.append(",PTITR=" + PTITR);
			sb.append(",PPARAG=" + PPARAG);
			sb.append(",PPOID=" + PPOID);
			sb.append(",PKLE=" + PKLE);
			sb.append(",PLIEN=" + PLIEN);
			sb.append(",PSFAM=" + PSFAM);
			sb.append(",PREF01=" + PREF01);
			sb.append(",PREF02=" + PREF02);
			sb.append(",PREF03=" + PREF03);
			sb.append(",PTYPE=" + PTYPE);
			sb.append(",PLIEU=" + PLIEU);
			sb.append(",PCATAL=" + PCATAL);
			sb.append(",PUV01=" + PUV01);
			sb.append(",PCONDI01=" + PCONDI01);
			sb.append(",PUV02=" + PUV02);
			sb.append(",PCONDI02=" + PCONDI02);
			sb.append(",PPROMDEB=" + PPROMDEB);
			sb.append(",PREMFOU=" + PREMFOU);
			sb.append(",PQTE01=" + PQTE01);
			sb.append(",PQTE02=" + PQTE02);
			sb.append(",PRX01=" + PRX01);
			sb.append(",PRX02=" + PRX02);
			sb.append(",PCUBAG=" + PCUBAG);
			sb.append(",PREVPOI=" + PREVPOI);
			sb.append(",PREVCUB=" + PREVCUB);
			sb.append(",PREVFAP=" + PREVFAP);
			sb.append(",PINTERNET=" + PINTERNET);
			sb.append(",PFIDEL=" + PFIDEL);
			sb.append(",PCOMMANDE=" + PCOMMANDE);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row45Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.PREF, other.PREF);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_6_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row45Struct row45 = new row45Struct();

				/**
				 * [tAdvancedHash_row45 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row45", false);
				start_Hash.put("tAdvancedHash_row45", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row45";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row45");
				}

				int tos_count_tAdvancedHash_row45 = 0;

				// connection name:row45
				// source node:tFileInputDelimited_6 - inputs:(after_tFileInputDelimited_5)
				// outputs:(row45,row45) | target node:tAdvancedHash_row45 - inputs:(row45)
				// outputs:()
				// linked node: tMap_5 - inputs:(row44,row45)
				// outputs:(copyOfcopyOfcopyOfcopyOfmain_2_3)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row45 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row45Struct> tHash_Lookup_row45 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row45Struct>getLookup(matchingModeEnum_row45);

				globalMap.put("tHash_Lookup_row45", tHash_Lookup_row45);

				/**
				 * [tAdvancedHash_row45 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_6 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_6", false);
				start_Hash.put("tFileInputDelimited_6", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_6";

				int tos_count_tFileInputDelimited_6 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_6 = new routines.system.RowState();

				class RowHelper_tFileInputDelimited_6 {

					public void valueToConn_0(org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_6,
							row45Struct row45) throws java.lang.Exception {

						int columnIndexWithD_tFileInputDelimited_6 = 0;

						columnIndexWithD_tFileInputDelimited_6 = 0;

						row45.PREF = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 1;

						row45.PDESIG1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 2;

						row45.PDESIG2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 3;

						row45.PDESIG3 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 4;

						row45.PPRIX = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 5;

						row45.PCTVA = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 6;

						row45.PCTYV = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 7;

						row45.PCPN = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 8;

						row45.PPA = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 9;

						row45.PQTE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 10;

						row45.PFORM01 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 11;

						row45.PFORM02 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 12;

						row45.PNBCAR = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 13;

						row45.PVERT = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 14;

						row45.PEMP2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 15;

						row45.PLIEU2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 16;

						row45.PREMIMAX = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 17;

						row45.FILLER2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 18;

						row45.PMOUV = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 19;

						row45.PTENU = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 20;

						row45.PINCRE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 21;

						row45.PPROMPA = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 22;

						row45.PACHAT = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 23;

						row45.PARRCDT1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 24;

						row45.PARRCDT2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 25;

						row45.PECOTAXE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 26;

						row45.PPAQUET = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 27;

						row45.PEMBAL = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 28;

						row45.PGRAMA = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 29;

						row45.PDATCRE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 30;

						row45.PDATPRIX = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 31;

						row45.PDATPA1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 32;

						row45.PPA1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 33;

						row45.PPV1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 34;

						row45.PPRIXR = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 35;

						row45.PPRIXRV1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 36;

						row45.PPRIXRV2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 37;

						row45.PPRIXRV3 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 38;

						row45.PPRIREV1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 39;

						row45.PPRIREV2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 40;

						row45.PPRIREV3 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 41;

						row45.PDATPV1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 42;

						row45.PMPA = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 43;

						row45.PETIQ = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 44;

						row45.PFOUR = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 45;

						row45.PPRITTC = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 46;

						row45.PIMOD = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 47;

						row45.PPROMREM = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 48;

						row45.PUV = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 49;

						row45.PUA = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 50;

						row45.PDELAI = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 51;

						row45.PMINI = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 52;

						row45.PMAXI = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 53;

						row45.PCDE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 54;

						row45.PEMP = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 55;

						row45.PPRI1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 56;

						row45.PPRI2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 57;

						row45.PPRI3 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 58;

						row45.PPRI4 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 59;

						row45.PPRI5 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 60;

						row45.PPRI6 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 61;

						row45.PREMI1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 62;

						row45.PREMI2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 63;

						row45.PREMI3 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 64;

						row45.PREMI4 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 65;

						row45.PREMI5 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 66;

						row45.PREMI6 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 67;

						row45.PQUANT1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 68;

						row45.PQUANT2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 69;

						row45.PQUANT3 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 70;

						row45.PQUANT4 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 71;

						row45.PQUANT5 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 72;

						row45.PQUANT6 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 73;

						row45.PEDAT = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 74;

						row45.PSDAT = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 75;

						row45.PRESV = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 76;

						row45.PREFOU = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 77;

						row45.PGAMEC = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 78;

						row45.PGAMER = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 79;

						row45.PGAMEN = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 80;

						row45.PTPF = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 81;

						row45.PSOMMEIL = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 82;

						row45.PINV = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 83;

						row45.PMAC = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 84;

						row45.PREJ = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 85;

						row45.PMOI = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 86;

						row45.P1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 87;

						row45.P2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 88;

						row45.P3 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 89;

						row45.P4 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 90;

						row45.P5 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 91;

						row45.P6 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 92;

						row45.P7 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 93;

						row45.P8 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 94;

						row45.P9 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 95;

						row45.P10 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 96;

						row45.P11 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 97;

						row45.P12 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 98;

						row45.PAN = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 99;

						row45.PAN1 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

					}

					public void valueToConn_1(org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_6,
							row45Struct row45) throws java.lang.Exception {

						int columnIndexWithD_tFileInputDelimited_6 = 0;

						columnIndexWithD_tFileInputDelimited_6 = 100;

						row45.PAN2 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 101;

						row45.PPROM = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 102;

						row45.PPROMD = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 103;

						row45.PCONDI = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 104;

						row45.PRESTE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 105;

						row45.PTITR = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 106;

						row45.PPARAG = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 107;

						row45.PPOID = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 108;

						row45.PKLE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 109;

						row45.PLIEN = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 110;

						row45.PSFAM = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 111;

						row45.PREF01 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 112;

						row45.PREF02 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 113;

						row45.PREF03 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 114;

						row45.PTYPE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 115;

						row45.PLIEU = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 116;

						row45.PCATAL = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 117;

						row45.PUV01 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 118;

						row45.PCONDI01 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 119;

						row45.PUV02 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 120;

						row45.PCONDI02 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 121;

						row45.PPROMDEB = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 122;

						row45.PREMFOU = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 123;

						row45.PQTE01 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 124;

						row45.PQTE02 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 125;

						row45.PRX01 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 126;

						row45.PRX02 = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 127;

						row45.PCUBAG = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 128;

						row45.PREVPOI = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 129;

						row45.PREVCUB = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 130;

						row45.PREVFAP = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 131;

						row45.PINTERNET = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 132;

						row45.PFIDEL = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

						columnIndexWithD_tFileInputDelimited_6 = 133;

						row45.PCOMMANDE = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

					}

					public void valueToConn(org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_6,
							row45Struct row45) throws java.lang.Exception {

						valueToConn_0(fid_tFileInputDelimited_6, row45);

						valueToConn_1(fid_tFileInputDelimited_6, row45);

					}

				}
				RowHelper_tFileInputDelimited_6 rowHelper_tFileInputDelimited_6 = new RowHelper_tFileInputDelimited_6();

				int nb_line_tFileInputDelimited_6 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_6 = null;
				int limit_tFileInputDelimited_6 = -1;
				try {

					Object filename_tFileInputDelimited_6 = "C:/DEV/Data/Base Montauban/Article.csv";
					if (filename_tFileInputDelimited_6 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_6 = 0, random_value_tFileInputDelimited_6 = -1;
						if (footer_value_tFileInputDelimited_6 > 0 || random_value_tFileInputDelimited_6 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_6 = new org.talend.fileprocess.FileInputDelimited(
								"C:/DEV/Data/Base Montauban/Article.csv", "ISO-8859-15", ";", "\n", true, 1, 0,
								limit_tFileInputDelimited_6, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_6 != null && fid_tFileInputDelimited_6.nextRecord()) {
						rowstate_tFileInputDelimited_6.reset();

						row45 = null;

						row45 = null;

						boolean whetherReject_tFileInputDelimited_6 = false;
						row45 = new row45Struct();
						try {

							rowHelper_tFileInputDelimited_6.valueToConn(fid_tFileInputDelimited_6, row45);

							if (rowstate_tFileInputDelimited_6.getException() != null) {
								throw rowstate_tFileInputDelimited_6.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_6 = true;

							System.err.println(e.getMessage());
							row45 = null;

						}

						/**
						 * [tFileInputDelimited_6 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_6 main ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						tos_count_tFileInputDelimited_6++;

						/**
						 * [tFileInputDelimited_6 main ] stop
						 */

						/**
						 * [tFileInputDelimited_6 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						/**
						 * [tFileInputDelimited_6 process_data_begin ] stop
						 */
// Start of branch "row45"
						if (row45 != null) {

							/**
							 * [tAdvancedHash_row45 main ] start
							 */

							currentComponent = "tAdvancedHash_row45";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row45"

								);
							}

							row45Struct row45_HashRow = new row45Struct();

							row45_HashRow.PREF = row45.PREF;

							row45_HashRow.PDESIG1 = row45.PDESIG1;

							row45_HashRow.PDESIG2 = row45.PDESIG2;

							row45_HashRow.PDESIG3 = row45.PDESIG3;

							row45_HashRow.PPRIX = row45.PPRIX;

							row45_HashRow.PCTVA = row45.PCTVA;

							row45_HashRow.PCTYV = row45.PCTYV;

							row45_HashRow.PCPN = row45.PCPN;

							row45_HashRow.PPA = row45.PPA;

							row45_HashRow.PQTE = row45.PQTE;

							row45_HashRow.PFORM01 = row45.PFORM01;

							row45_HashRow.PFORM02 = row45.PFORM02;

							row45_HashRow.PNBCAR = row45.PNBCAR;

							row45_HashRow.PVERT = row45.PVERT;

							row45_HashRow.PEMP2 = row45.PEMP2;

							row45_HashRow.PLIEU2 = row45.PLIEU2;

							row45_HashRow.PREMIMAX = row45.PREMIMAX;

							row45_HashRow.FILLER2 = row45.FILLER2;

							row45_HashRow.PMOUV = row45.PMOUV;

							row45_HashRow.PTENU = row45.PTENU;

							row45_HashRow.PINCRE = row45.PINCRE;

							row45_HashRow.PPROMPA = row45.PPROMPA;

							row45_HashRow.PACHAT = row45.PACHAT;

							row45_HashRow.PARRCDT1 = row45.PARRCDT1;

							row45_HashRow.PARRCDT2 = row45.PARRCDT2;

							row45_HashRow.PECOTAXE = row45.PECOTAXE;

							row45_HashRow.PPAQUET = row45.PPAQUET;

							row45_HashRow.PEMBAL = row45.PEMBAL;

							row45_HashRow.PGRAMA = row45.PGRAMA;

							row45_HashRow.PDATCRE = row45.PDATCRE;

							row45_HashRow.PDATPRIX = row45.PDATPRIX;

							row45_HashRow.PDATPA1 = row45.PDATPA1;

							row45_HashRow.PPA1 = row45.PPA1;

							row45_HashRow.PPV1 = row45.PPV1;

							row45_HashRow.PPRIXR = row45.PPRIXR;

							row45_HashRow.PPRIXRV1 = row45.PPRIXRV1;

							row45_HashRow.PPRIXRV2 = row45.PPRIXRV2;

							row45_HashRow.PPRIXRV3 = row45.PPRIXRV3;

							row45_HashRow.PPRIREV1 = row45.PPRIREV1;

							row45_HashRow.PPRIREV2 = row45.PPRIREV2;

							row45_HashRow.PPRIREV3 = row45.PPRIREV3;

							row45_HashRow.PDATPV1 = row45.PDATPV1;

							row45_HashRow.PMPA = row45.PMPA;

							row45_HashRow.PETIQ = row45.PETIQ;

							row45_HashRow.PFOUR = row45.PFOUR;

							row45_HashRow.PPRITTC = row45.PPRITTC;

							row45_HashRow.PIMOD = row45.PIMOD;

							row45_HashRow.PPROMREM = row45.PPROMREM;

							row45_HashRow.PUV = row45.PUV;

							row45_HashRow.PUA = row45.PUA;

							row45_HashRow.PDELAI = row45.PDELAI;

							row45_HashRow.PMINI = row45.PMINI;

							row45_HashRow.PMAXI = row45.PMAXI;

							row45_HashRow.PCDE = row45.PCDE;

							row45_HashRow.PEMP = row45.PEMP;

							row45_HashRow.PPRI1 = row45.PPRI1;

							row45_HashRow.PPRI2 = row45.PPRI2;

							row45_HashRow.PPRI3 = row45.PPRI3;

							row45_HashRow.PPRI4 = row45.PPRI4;

							row45_HashRow.PPRI5 = row45.PPRI5;

							row45_HashRow.PPRI6 = row45.PPRI6;

							row45_HashRow.PREMI1 = row45.PREMI1;

							row45_HashRow.PREMI2 = row45.PREMI2;

							row45_HashRow.PREMI3 = row45.PREMI3;

							row45_HashRow.PREMI4 = row45.PREMI4;

							row45_HashRow.PREMI5 = row45.PREMI5;

							row45_HashRow.PREMI6 = row45.PREMI6;

							row45_HashRow.PQUANT1 = row45.PQUANT1;

							row45_HashRow.PQUANT2 = row45.PQUANT2;

							row45_HashRow.PQUANT3 = row45.PQUANT3;

							row45_HashRow.PQUANT4 = row45.PQUANT4;

							row45_HashRow.PQUANT5 = row45.PQUANT5;

							row45_HashRow.PQUANT6 = row45.PQUANT6;

							row45_HashRow.PEDAT = row45.PEDAT;

							row45_HashRow.PSDAT = row45.PSDAT;

							row45_HashRow.PRESV = row45.PRESV;

							row45_HashRow.PREFOU = row45.PREFOU;

							row45_HashRow.PGAMEC = row45.PGAMEC;

							row45_HashRow.PGAMER = row45.PGAMER;

							row45_HashRow.PGAMEN = row45.PGAMEN;

							row45_HashRow.PTPF = row45.PTPF;

							row45_HashRow.PSOMMEIL = row45.PSOMMEIL;

							row45_HashRow.PINV = row45.PINV;

							row45_HashRow.PMAC = row45.PMAC;

							row45_HashRow.PREJ = row45.PREJ;

							row45_HashRow.PMOI = row45.PMOI;

							row45_HashRow.P1 = row45.P1;

							row45_HashRow.P2 = row45.P2;

							row45_HashRow.P3 = row45.P3;

							row45_HashRow.P4 = row45.P4;

							row45_HashRow.P5 = row45.P5;

							row45_HashRow.P6 = row45.P6;

							row45_HashRow.P7 = row45.P7;

							row45_HashRow.P8 = row45.P8;

							row45_HashRow.P9 = row45.P9;

							row45_HashRow.P10 = row45.P10;

							row45_HashRow.P11 = row45.P11;

							row45_HashRow.P12 = row45.P12;

							row45_HashRow.PAN = row45.PAN;

							row45_HashRow.PAN1 = row45.PAN1;

							row45_HashRow.PAN2 = row45.PAN2;

							row45_HashRow.PPROM = row45.PPROM;

							row45_HashRow.PPROMD = row45.PPROMD;

							row45_HashRow.PCONDI = row45.PCONDI;

							row45_HashRow.PRESTE = row45.PRESTE;

							row45_HashRow.PTITR = row45.PTITR;

							row45_HashRow.PPARAG = row45.PPARAG;

							row45_HashRow.PPOID = row45.PPOID;

							row45_HashRow.PKLE = row45.PKLE;

							row45_HashRow.PLIEN = row45.PLIEN;

							row45_HashRow.PSFAM = row45.PSFAM;

							row45_HashRow.PREF01 = row45.PREF01;

							row45_HashRow.PREF02 = row45.PREF02;

							row45_HashRow.PREF03 = row45.PREF03;

							row45_HashRow.PTYPE = row45.PTYPE;

							row45_HashRow.PLIEU = row45.PLIEU;

							row45_HashRow.PCATAL = row45.PCATAL;

							row45_HashRow.PUV01 = row45.PUV01;

							row45_HashRow.PCONDI01 = row45.PCONDI01;

							row45_HashRow.PUV02 = row45.PUV02;

							row45_HashRow.PCONDI02 = row45.PCONDI02;

							row45_HashRow.PPROMDEB = row45.PPROMDEB;

							row45_HashRow.PREMFOU = row45.PREMFOU;

							row45_HashRow.PQTE01 = row45.PQTE01;

							row45_HashRow.PQTE02 = row45.PQTE02;

							row45_HashRow.PRX01 = row45.PRX01;

							row45_HashRow.PRX02 = row45.PRX02;

							row45_HashRow.PCUBAG = row45.PCUBAG;

							row45_HashRow.PREVPOI = row45.PREVPOI;

							row45_HashRow.PREVCUB = row45.PREVCUB;

							row45_HashRow.PREVFAP = row45.PREVFAP;

							row45_HashRow.PINTERNET = row45.PINTERNET;

							row45_HashRow.PFIDEL = row45.PFIDEL;

							row45_HashRow.PCOMMANDE = row45.PCOMMANDE;

							tHash_Lookup_row45.put(row45_HashRow);

							tos_count_tAdvancedHash_row45++;

							/**
							 * [tAdvancedHash_row45 main ] stop
							 */

							/**
							 * [tAdvancedHash_row45 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row45";

							/**
							 * [tAdvancedHash_row45 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row45 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row45";

							/**
							 * [tAdvancedHash_row45 process_data_end ] stop
							 */

						} // End of branch "row45"

						/**
						 * [tFileInputDelimited_6 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						/**
						 * [tFileInputDelimited_6 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_6 end ] start
						 */

						currentComponent = "tFileInputDelimited_6";

					}
				} finally {
					if (!((Object) ("C:/DEV/Data/Base Montauban/Article.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_6 != null) {
							fid_tFileInputDelimited_6.close();
						}
					}
					if (fid_tFileInputDelimited_6 != null) {
						globalMap.put("tFileInputDelimited_6_NB_LINE", fid_tFileInputDelimited_6.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_6", true);
				end_Hash.put("tFileInputDelimited_6", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_6 end ] stop
				 */

				/**
				 * [tAdvancedHash_row45 end ] start
				 */

				currentComponent = "tAdvancedHash_row45";

				tHash_Lookup_row45.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row45");
				}

				ok_Hash.put("tAdvancedHash_row45", true);
				end_Hash.put("tAdvancedHash_row45", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row45 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_6 finally ] start
				 */

				currentComponent = "tFileInputDelimited_6";

				/**
				 * [tFileInputDelimited_6 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row45 finally ] start
				 */

				currentComponent = "tAdvancedHash_row45";

				/**
				 * [tAdvancedHash_row45 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_6_SUBPROCESS_STATE", 1);
	}

	public void tPrejob_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tPrejob_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tPrejob_1 begin ] start
				 */

				ok_Hash.put("tPrejob_1", false);
				start_Hash.put("tPrejob_1", System.currentTimeMillis());

				currentComponent = "tPrejob_1";

				int tos_count_tPrejob_1 = 0;

				/**
				 * [tPrejob_1 begin ] stop
				 */

				/**
				 * [tPrejob_1 main ] start
				 */

				currentComponent = "tPrejob_1";

				tos_count_tPrejob_1++;

				/**
				 * [tPrejob_1 main ] stop
				 */

				/**
				 * [tPrejob_1 process_data_begin ] start
				 */

				currentComponent = "tPrejob_1";

				/**
				 * [tPrejob_1 process_data_begin ] stop
				 */

				/**
				 * [tPrejob_1 process_data_end ] start
				 */

				currentComponent = "tPrejob_1";

				/**
				 * [tPrejob_1 process_data_end ] stop
				 */

				/**
				 * [tPrejob_1 end ] start
				 */

				currentComponent = "tPrejob_1";

				ok_Hash.put("tPrejob_1", true);
				end_Hash.put("tPrejob_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk2", 0, "ok");
				}
				tDBConnection_1Process(globalMap);

				/**
				 * [tPrejob_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tPrejob_1 finally ] start
				 */

				currentComponent = "tPrejob_1";

				/**
				 * [tPrejob_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tPrejob_1_SUBPROCESS_STATE", 1);
	}

	public void tDBConnection_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBConnection_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tDBConnection_1 begin ] start
				 */

				ok_Hash.put("tDBConnection_1", false);
				start_Hash.put("tDBConnection_1", System.currentTimeMillis());

				currentComponent = "tDBConnection_1";

				int tos_count_tDBConnection_1 = 0;

				String dbProperties_tDBConnection_1 = "";
				String url_tDBConnection_1 = "jdbc:postgresql://" + "localhost" + ":" + "5432" + "/" + "syg";

				if (dbProperties_tDBConnection_1 != null && !"".equals(dbProperties_tDBConnection_1.trim())) {
					url_tDBConnection_1 = url_tDBConnection_1 + "?" + dbProperties_tDBConnection_1;
				}
				String dbUser_tDBConnection_1 = "postgres";

				final String decryptedPassword_tDBConnection_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:8WgBx4ahzbjKureIHAMeWsSNUCkcd7h8Z9EJSwjUxkxWvFUi8g==");
				String dbPwd_tDBConnection_1 = decryptedPassword_tDBConnection_1;

				java.sql.Connection conn_tDBConnection_1 = null;

				java.util.Enumeration<java.sql.Driver> drivers_tDBConnection_1 = java.sql.DriverManager.getDrivers();
				java.util.Set<String> redShiftDriverNames_tDBConnection_1 = new java.util.HashSet<String>(
						java.util.Arrays.asList("com.amazon.redshift.jdbc.Driver", "com.amazon.redshift.jdbc41.Driver",
								"com.amazon.redshift.jdbc42.Driver"));
				while (drivers_tDBConnection_1.hasMoreElements()) {
					java.sql.Driver d_tDBConnection_1 = drivers_tDBConnection_1.nextElement();
					if (redShiftDriverNames_tDBConnection_1.contains(d_tDBConnection_1.getClass().getName())) {
						try {
							java.sql.DriverManager.deregisterDriver(d_tDBConnection_1);
							java.sql.DriverManager.registerDriver(d_tDBConnection_1);
						} catch (java.lang.Exception e_tDBConnection_1) {
							globalMap.put("tDBConnection_1_ERROR_MESSAGE", e_tDBConnection_1.getMessage());
							// do nothing
						}
					}
				}
				String driverClass_tDBConnection_1 = "org.postgresql.Driver";
				java.lang.Class jdbcclazz_tDBConnection_1 = java.lang.Class.forName(driverClass_tDBConnection_1);
				globalMap.put("driverClass_tDBConnection_1", driverClass_tDBConnection_1);

				conn_tDBConnection_1 = java.sql.DriverManager.getConnection(url_tDBConnection_1, dbUser_tDBConnection_1,
						dbPwd_tDBConnection_1);

				globalMap.put("conn_tDBConnection_1", conn_tDBConnection_1);
				if (null != conn_tDBConnection_1) {

					conn_tDBConnection_1.setAutoCommit(false);
				}

				globalMap.put("schema_" + "tDBConnection_1", "");

				/**
				 * [tDBConnection_1 begin ] stop
				 */

				/**
				 * [tDBConnection_1 main ] start
				 */

				currentComponent = "tDBConnection_1";

				tos_count_tDBConnection_1++;

				/**
				 * [tDBConnection_1 main ] stop
				 */

				/**
				 * [tDBConnection_1 process_data_begin ] start
				 */

				currentComponent = "tDBConnection_1";

				/**
				 * [tDBConnection_1 process_data_begin ] stop
				 */

				/**
				 * [tDBConnection_1 process_data_end ] start
				 */

				currentComponent = "tDBConnection_1";

				/**
				 * [tDBConnection_1 process_data_end ] stop
				 */

				/**
				 * [tDBConnection_1 end ] start
				 */

				currentComponent = "tDBConnection_1";

				ok_Hash.put("tDBConnection_1", true);
				end_Hash.put("tDBConnection_1", System.currentTimeMillis());

				/**
				 * [tDBConnection_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBConnection_1 finally ] start
				 */

				currentComponent = "tDBConnection_1";

				/**
				 * [tDBConnection_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBConnection_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final FinalJobPart2 FinalJobPart2Class = new FinalJobPart2();

		int exitCode = FinalJobPart2Class.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = FinalJobPart2.class.getClassLoader()
					.getResourceAsStream("talend_montauban/finaljobpart2_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = FinalJobPart2.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		try {
			errorCode = null;
			tPrejob_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tPrejob_1) {
			globalMap.put("tPrejob_1_SUBPROCESS_STATE", -1);

			e_tPrejob_1.printStackTrace();

		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out
					.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : FinalJobPart2");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {
		closeSqlDbConnections();

	}

	private void closeSqlDbConnections() {
		try {
			Object obj_conn;
			obj_conn = globalMap.remove("conn_tDBConnection_1");
			if (null != obj_conn) {
				((java.sql.Connection) obj_conn).close();
			}
		} catch (java.lang.Exception e) {
		}
	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();
		connections.put("conn_tDBConnection_1", globalMap.get("conn_tDBConnection_1"));

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 415531 characters generated by Talend Open Studio for Data Integration on the
 * 31 mars 2022 à 11:27:00 CEST
 ************************************************************************************************/