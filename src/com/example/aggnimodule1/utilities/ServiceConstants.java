package com.example.aggnimodule1.utilities;

/**
 * This class holds all definitions with constants required for the Service
 * Layer.
 * 
 * @author Melvin Lobo
 */
public class ServiceConstants {

	/**
	 * Flag to check if URL's are pointing to local dev servers
	 */
	public static final boolean isDevMode = false;

	/**
	 * Enum to identify the connection type
	 */
	public static enum CONN_TYPE {
		HTTP, HTTPS
	};

	/**
	 * Enum to identify the build type
	 */
	public static enum BUILD_TYPE {
		PROD, PROD_LIKE, PROD_TEST, DEV, LOCAL, EC2, DMZ_PROD_LIKE, DMZ_PROD
	};

	/**
	 * The build type param Note: Change this param to change the build type
	 */
	public static final BUILD_TYPE BUILD = BUILD_TYPE.PROD_TEST;

	/**
	 * The Base URL's
	 */
	public static final String BASE_URL_PROD_TEST = "https://coreblueservices-pt.na.bestbuy.com";
	public static final String BASE_URL_PROD_TEST_L7 = "https://dng01apl.na.bestbuy.com:8443/Programs/CoreBlueServiceREST";
	public static final String BASE_URL_PROD_LIKE = BASE_URL_PROD_TEST_L7;
	public static final String BASE_URL_PROD = "https://tagservices.na.bestbuy.com/Programs/CoreBlueServiceREST";

	/*
	 * Coreblue URLs for reference
	 * 
	 * PROD_TEST = https://coreblueservices-pt.na.bestbuy.com PROD_TEST_L7 =
	 * https://dng01apl.na.bestbuy.com:8443/Programs/CoreBlueServiceREST
	 * PROD_LIKE = https://coreblueservices-pl.bestbuy.com PROD_LIKE_L7 = ?
	 * PROD_BETA = https://coreblueservices-beta.bestbuy.com PROD_BETA_L7 =
	 * https://tagservices.na.bestbuy.com/Programs/Beta/CoreBlueServiceREST PROD
	 * = https://cbgservices.bestbuy.com PROD_L7 =
	 * https://tagservices.na.bestbuy.com/Programs/CoreBlueServiceREST
	 */
	/**
	 * The Base URL's for DMZ
	 */
	public static final String DMZ_BASE_URL_PROD_LIKE = "https://sami-pl.bestbuymobile.com";
	public static final String DMZ_BASE_URL_PROD = "https://www.sami.bestbuymobile.com";

	/**
	 * The Base URL's for Merchant Audit
	 */
	public static final String MA_BASE_URL_PROD = "https://www.sami.bestbuymobile.com/MerchantAudit";
	public static final String MA_BASE_URL_PROD_LIKE = "https://sami-pl.bestbuymobile.com/MerchantAudit";
	public static final String MA_BASE_URL_DEV = "http://ec2\u002d107\u002d20\u002d224\u002d41.compute\u002d1.amazonaws.com/MerchantAudit";
	public static final String MA_BASE_URL_LOCAL = "http://10.20.3.143:8080/MerchantAudit";

	/**
	 * The Base URL's for Scheduling
	 * 
	 * Note: As part of the temporary fix, pointing the whole build to PROD-LIKE
	 * will point the scheduling system to the dev server. Change URLs
	 * accordingly.
	 */
	public static final String SCH_BASE_PROD = "https://scheduling.bestbuymobile.com/rest/v2/"; // Biplab:
																								// changed
																								// after
																								// confirming,
																								// 21_Nov_2013
	public static final String SCH_BASE_PROD_LIKE = "https://scheduling2.pl.bestbuymobile.com/rest/v2/"; // Biplab:
																											// changed
																											// after
																											// confirming,
																											// 21_Nov_2013
	public static final String SCH_BASE_DEV = "http://simbbdev3.systemsinmotion.com/rest/v2/";

	/**
	 * Coreblue services version number <br>
	 * This needs to be part of the URL context path <br>
	 * Developers need to verify this with the services team during each release
	 */
	public static final String COREBLUE_SERVICE_VERSION = "/V4.0";

	/**
	 * Base URL to connect to REST Service (Prod)
	 */
	public static final String SERVICE_RESOURCE = "/coreblueservices"
			+ ServiceConstants.COREBLUE_SERVICE_VERSION;

	/**
	 * Resource path for getting weekly deals
	 */
	public static final String RESOURCE_GET_WEEKLY_DEALS = "/deals/weekly/insert";

	/**
	 * Resource for category count
	 */
	public static final String RESOURCE_WEEKLY_DEALS_CATEGORY_COUNT = "/categories";

	/**
	 * Resource for flyers
	 */
	public static final String RESOURCE_FLYERS = "/flyer";

	/**
	 * Query parameter "from" page
	 */
	public static final String QUERY_PAGE_FROM = "from=";

	/**
	 * Query Parameter "size" of page
	 */
	public static final String QUERY_PAGE_SIZE = "size=";

	/**
	 * Resource for feedback
	 */
	public static final String RESOURCE_FEEDBACK = "/feedback";

	/**
	 * Resource for the field show all in the url
	 */
	public static final String SHOW_ALL = "?showAll=true";

	/**
	 * Resource for search
	 */
	public static final String RESOURCE_SEARCH = "/search";

	/**
	 * Resource for search pdp
	 */
	public static final String RESOURCE_SEARCH_PDP = "/pdp";

	/**
	 * Resource for search scanned product
	 */
	public static final String RESOURCE_SEARCH_SCAN = "/scan";

	/**
	 * Resource for scan type qr
	 */
	public static final String RESOURCE_SEARCH_SCAN_TYPE_QR = "/QR";

	/**
	 * Resource for scan type bar
	 */
	public static final String RESOURCE_SEARCH_SCAN_TYPE_BAR = "/BAR";

	/**
	 * Resource for product review
	 */
	public static final String RESOURCE_PRODUCT_REVIEW = "/reviews";

	/**
	 * Resource for product review
	 */
	public static final String RESOURCE_PRODUCT_REVIEW_PAGE_COUNT = "?pageNumber=";

	/**
	 * Resource for Inkfinder
	 */
	public static final String RESOURCE_INKFINDER = "/ink";

	/**
	 * Resource for Inkfinder
	 */
	public static final String RESOURCE_SKU = "/sku";

	/**
	 * Resource for upc
	 */
	public static final String RESOURCE_UPC = "/upc";

	/**
	 * Resource for Similar products
	 */
	public static final String RESOURCE_SIMILAR_PRODUCTS = "/similarProducts";

	/**
	 * Resource for Batch
	 */
	public static final String RESOURCE_BATCH = "/skuList";

	/**
	 * The resource for search text
	 */
	public static final String RESOURCE_SEARCH_TEXT = "/text";

	/**
	 * Resource for login
	 */
	public static final String RESOURCE_LOGIN = "/login";

	/**
	 * The resource for fulfillment
	 */
	public static final String RESOURCE_FULFILLMENT = "/fulfillment";

	/**
	 * The resource for store
	 */
	public static final String RESOURCE_STORE = "/store";

	/**
	 * The resource for store
	 */
	public static final String RESOURCE_STORES = "/stores";

	/**
	 * The resource for customer
	 */
	public static final String RESOURCE_CUSTOMER = "/customer";

	/**
	 * The resource for subscriptions
	 */
	public static final String RESOURCE_SUBSCRIPTIONS = "/subscriptions";

	/**
	 * The resource for deals
	 */
	public static final String RESOURCE_DEALS = "/deals";

	/**
	 * The resource for product
	 */
	public static final String RESOURCE_PRODUCT = "/product";

	/**
	 * BlueList resource paths for GSP Plans
	 */
	public static final String GSP_PLANS = "/gsp";

	public static final String METHOD_PENDINGTASKCOUNT = "/GetPendingTaskCount";
	public static final String METHOD_TODAYS_APPOINTMENT = "GetTodaysAppointments";
	public static final String METHOD_CREATE_WALKIN = "/CreateAppointment";

	public static final String RESOURCE_API_KEY = "/API-KEY";

	public static final String RESOURCE_SIGNATURE = "/SIGNATURE";

	public static final String RESOURCE_CREATE = "/create";

	/**
	 * BlueList resource paths for GSTechSupport Plans
	 */
	public static final String GS_TECH_SUPPORT = "/techSupportProducts";

	/**
	 * BlueList resource paths for Accessories
	 */
	public static final String COMPATIBLE_PRODUCTS = "/compatibleProducts";

	/**
	 * Resource path for Recommendations
	 */
	public static final String RECOMMENDATION = "/recommendations";

	/**
	 * BlueList resource
	 */
	public static final String BLUELIST = "/bluelist";

	/**
	 * MA Resource Paths
	 */
	public static final String MA_UPLOAD_TASK = "/UploadTaskServlet";
	public static final String MA_STORE_TASK = "/GetStoreTasksServlet?storeId=";
	public static final String MA_GET_TASK = "/jsp/deviceinterface/gettask.jsp?taskId=";
	public static final String MA_COMPLETED_TASK = "/GetCompletedTasksServlet?storeId=";
	public static final String MA_TASK_DETAILS = "/GetTaskDetailsServlet?taskId=";

	/**
	 * Scheduling Resource Paths
	 */
	public static final String SCH_LOGIN = "LoginUser/format/json";

	/**
	 * String to identify the kill switch error
	 */
	public static final String ERR_KILL_SWITCH = "[KILL SWITCH ENABLED]";

	/**
	 * Upgrade check base URL to connect to REST Service (Prod Test)
	 */
	public static final String UCS_SERVICE_RESOURCE = "/drp-war/drp";

	/**
	 * Upgrade check base URL to connect to CBG Service (Prod Test)
	 */
	public static final String UCS_CBG_RESOURCE = "/cbg/service/v1";

	/**
	 * Upgrade check base URL to connect to resource (Prod Test)
	 */
	public static final String UCS_RESOURCE = "/customer-subscription/search";

	/**
	 * URL to connect USC EMail opt in
	 */
	public static final String UCS_EMAIL_RESOURCE = "/upgrade-notification-optin";

	/**
	 * URL to connect USC REST
	 */
	public static final String UCS_REST_RESOURCE = "/rest";

	/**
	 * URL to connect to device information
	 */
	public static final String UCS_CARRIER_TREE_RESOURCE = "/carrierTree";
	/**
	 * URL to connect to Device tree
	 */
	public static final String UCS_DEVICE_TREE_RESOURCE = "/deviceTree";

	/**
	 * URL to connect to coreblue
	 */
	public static final String HOME_THEATER_COREBLUE = "/coreblueadmin";

	/**
	 * URL to connect Home theatre resource
	 */
	public static final String HOME_THEATER_RESOURCE = "/resources";

	/**
	 * URL to Home theater
	 */
	public static final String HOME_THEATER = "/HomeTheater";

	/**
	 * Format json
	 */
	public static final String SCH_FORMAT_JSON = "/format/json";

	public static final String RESOURCE_CUSTOMER_SEARCH_SERVICE = "/customer/search";

	public static final String RESOURCE_CUSTOMER_ACCOUNT_DETAILS_SERVICE = "/customer/%s/account/details";

	public static final String RESOURCE_CUSTOMER_PURCHASE_HISTORY_BY_DATE = "/customer/%s/purchasehistory?fromDate=%s&toDate=%s";

	public static final String RESOURCE_CUSTOMER_PURCHASE_HISTORY_BY_HISTORY_CODE = "/customer/%s/purchasehistory?period=%s";

	public static final String HEADER_FIELD_USERID = "userId";

	public static final String RESOURCE_CREATE_CUSTOMER = "/customer/%s";

	public static final String RESOURCE_UPDATE_CUSTOMER = "/customer/%s?enrollMyBBY=%s";

	public static final String RESOURCE_GET_MOBILE_PROTECTION_PLAND_AND_PROMO = "/customer/mobile/subscriptions/search";

	public static final String RESOURCE_APPLICATION_CONFIGURATION = "/application/config";

	public static final String PROMOTIONS = "/promotion/register";

	public static final String RESOURCE_RETRIEVE_BLUELISTS = "/bluelist?listIds=%s";

	public static final String RESOURCE_GET_OFFER_PRICE_AND_PROMOTIONS = "/bluelist/getOfferPricesandPromotions";

	public static final String RESOURCE_UPDATE_BLUELIST = "/bluelist/%s?target=%s";

	public static final String RESOURCE_DELETE_BLUELIST = "/bluelist/%s/partyId/%s";

	public static final String RESOURCE_CREATE_BLUELIST = "/bluelist?target=%s";

	// Service input parameters

	public static final String BLUELIST_TENDER_OPTIONS_SAVE_AND_TENDER_BOTH = "master,tender";

	public static final String BLUELIST_TENDER_OPTIONS_SAVE_ONLY = "master";

	public static final String BLUELIST_TENDER_OPTIONS_TENDER_ONLY = "tender";

	public static final String BLUELIST_OPTION_DELETE_RETAILLISTID = "/retailList/%s";

	// Error types
	public static final String CUSTOMER_SEARCH_UPGRADE_CHECK_ERROR_TYPE = "UPGRADE_CHECK";

	public static final String CUSTOMER_SEARCH_BBY_SEARCH_ERROR_TYPE = "PURCHASE_HISTORY";

	// Constants used in services
	public static final String SERVICE_TYPE_GSP = "GSP";

	public static final String SERVICE_TYPE_GSTS = "GSTP";

	public static final String SERVICE_TYPE_S2 = "S2";

	/**
	 * Static function to get teh base URL for coreBLUE based on the build
	 * 
	 * @author Melvin Lobo
	 */
	public static String getBaseURL() {
		String sBaseURL = "";

		switch (BUILD) {
		case PROD_LIKE:
			sBaseURL = BASE_URL_PROD_LIKE;
			break;
		case PROD:
			sBaseURL = BASE_URL_PROD;
			break;
		case PROD_TEST:
			sBaseURL = BASE_URL_PROD_TEST;
			break;
		case DMZ_PROD:
			sBaseURL = DMZ_BASE_URL_PROD;
			break;
		case DMZ_PROD_LIKE:
			sBaseURL = DMZ_BASE_URL_PROD_LIKE;
			break;
		default:
			sBaseURL = DMZ_BASE_URL_PROD_LIKE;
			break;
		}
		return sBaseURL;
	}

	/**
	 * Static function to get the Base URL for MA
	 * 
	 * @author Melvin Lobo
	 */
	public static String getMABaseURL() {
		String sBaseURL = "";

		switch (BUILD) {
		// case PROD_LIKE:
		// sBaseURL = MA_BASE_URL_PROD_LIKE;
		// break;
		case PROD:
			sBaseURL = MA_BASE_URL_PROD;
			break;
		case LOCAL:
			sBaseURL = MA_BASE_URL_LOCAL;
			break;
		case DEV:
			sBaseURL = MA_BASE_URL_DEV;
			break;
		// case PROD_TEST:
		// sBaseURL = BASE_URL_PROD_TEST;
		// break;
		default:
			sBaseURL = MA_BASE_URL_PROD_LIKE;
			break;
		}
		return sBaseURL;
	}

	/**
	 * Static function to get the Base URL for Scheduling
	 * 
	 * @author Melvin Lobo
	 */
	public static String getSchedulingBaseURL() {
		String sBaseURL = "";

		switch (BUILD) {
		// case PROD_LIKE:
		// sBaseURL = SCH_BASE_PROD;
		// break;
		case PROD:
			sBaseURL = SCH_BASE_PROD;
			break;
		case DEV:
			sBaseURL = SCH_BASE_DEV;
			break;
		// case PROD_TEST:
		// sBaseURL = SCH_BASE_PROD;
		// break;
		default:
			sBaseURL = SCH_BASE_PROD;
			break;
		}
		return sBaseURL;
	}

	/*	*//**
	 * Static function to get the Scheduling API Key
	 * 
	 * @author Melvin Lobo
	 */
	/*
	 * public static String getSchedulingAPIKey() {
	 * 
	 * String sBaseURL = "";
	 * sBaseURL=CoreBlueApplication.getmSchedulingAPIKey();
	 * 
	 * return sBaseURL; }
	 *//**
	 * Static function to get the Scheduling Secret Key
	 * 
	 * @author Melvin Lobo
	 */
	/*
	 * public static String getSchedulingSecretKey() {
	 * 
	 * String sBaseURL = "";
	 * sBaseURL=CoreBlueApplication.getmSchedulingSecretKey();
	 * 
	 * return sBaseURL; }
	 *//**
	 * Static function to get the certificate
	 * 
	 * @author Melvin Lobo
	 */
	/*
	 * public static int getCertificate() { int nCertID = 0;
	 * 
	 * switch (BUILD) { case PROD_LIKE: nCertID =
	 * com.bestbuy.coreblue.R.raw.upgradechecker; // nCertID =
	 * com.bestbuy.coreblue.R.raw.beast; break; case PROD: nCertID =
	 * com.bestbuy.coreblue.R.raw.upgradechecker; break; case EC2: nCertID =
	 * com.bestbuy.coreblue.R.raw.samipl; break; case PROD_TEST: nCertID =
	 * com.bestbuy.coreblue.R.raw.upgradechecker; break; case DMZ_PROD: nCertID
	 * = com.bestbuy.coreblue.R.raw.coreblueprod; break; case DMZ_PROD_LIKE:
	 * nCertID = com.bestbuy.coreblue.R.raw.samipl; break; default: nCertID =
	 * com.bestbuy.coreblue.R.raw.samipl; break; } return nCertID; }
	 *//**
	 * Static function to get the certificate password
	 * 
	 * @author Melvin Lobo
	 */
	/*
	 * public static int getCertificatePassword() { int nCertID = 0;
	 * 
	 * switch (BUILD) { case PROD_LIKE: nCertID =
	 * com.bestbuy.coreblue.R.string.keystore_password_pt; // nCertID =
	 * com.bestbuy.coreblue.R.string.keystore_password; break; case PROD:
	 * nCertID = com.bestbuy.coreblue.R.string.keystore_password_pt; break; case
	 * EC2: nCertID = com.bestbuy.coreblue.R.string.keystore_password; break;
	 * case PROD_TEST: nCertID =
	 * com.bestbuy.coreblue.R.string.keystore_password_pt; break; case DMZ_PROD:
	 * nCertID = com.bestbuy.coreblue.R.string.keystore_password; break; case
	 * DMZ_PROD_LIKE: nCertID = com.bestbuy.coreblue.R.string.keystore_password;
	 * break; default: nCertID =
	 * com.bestbuy.coreblue.R.string.keystore_password; break; } return nCertID;
	 * }
	 *//**
	 * Static function to return the build type
	 * 
	 * @author Melvin Lobo
	 */
	/*
	 * public static BUILD_TYPE getBuildType() { return BUILD; }
	 *//**
	 * Get the service URL
	 * 
	 * @author Melvin Lobo
	 */
	/*
	 * public static String getServiceURL() { return (getBaseURL() +
	 * SERVICE_RESOURCE); }
	 *//**
	 * Static function to get the connection type based on the URL
	 * 
	 * @author Melvin Lobo
	 */
	public static CONN_TYPE getConnectionType() {
		if (getBaseURL().contains("https://"))
			return CONN_TYPE.HTTPS;
		else
			return CONN_TYPE.HTTP;
	}

	/**
	 * Static function to get the DMZ certificate
	 * 
	 * @author BiplabP
	 */
	/*
	 * public static int getDMZCertificate() { int nCertID = 0;
	 * 
	 * switch (BUILD) { case PROD_LIKE: nCertID =
	 * com.bestbuy.coreblue.R.raw.samipl; break; case PROD: nCertID =
	 * com.bestbuy.coreblue.R.raw.coreblueprod; break; default: nCertID =
	 * com.bestbuy.coreblue.R.raw.samipl; break; } return nCertID; }
	 *//**
	 * Static function to get the DMZ certificate password
	 * 
	 * @author BiplabP
	 */
	/*
	 * public static int getDMZCertificatePassword() { int nCertID = 0;
	 * 
	 * switch (BUILD) { case PROD_LIKE: nCertID =
	 * com.bestbuy.coreblue.R.string.keystore_password; break; case PROD:
	 * nCertID = com.bestbuy.coreblue.R.string.keystore_password; break;
	 * default: nCertID = com.bestbuy.coreblue.R.string.keystore_password;
	 * break; } return nCertID; }
	 *//**
	 * Method to get the Scheduling bks file
	 * 
	 * @return
	 * 
	 * @author BiplabP
	 */
	/*
	 * public static int getSchedulingCertificate(){ int nCertID = 0;
	 * 
	 * switch (BUILD) { // case PROD_LIKE: // nCertID =
	 * com.bestbuy.coreblue.R.raw.coreblue_sch_pl; // break; case PROD: nCertID
	 * = com.bestbuy.coreblue.R.raw.coreblue_sch_prod; break; default: nCertID =
	 * com.bestbuy.coreblue.R.raw.coreblue_sch_prod; break; } return nCertID; }
	 */

}
