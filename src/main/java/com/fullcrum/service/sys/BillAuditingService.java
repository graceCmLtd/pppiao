package com.fullcrum.service.sys;

import java.util.List;
import java.util.Map;

public interface BillAuditingService {

	public List<Map<String, Object>> getBillInfo(String billNumber);

	public void updateBillStatus(String billNumber, String status, String failReason);

	public List<Map<String, Object>> getBills();

}
