package com.fullcrum.service.sys;

import java.util.List;
import java.util.Map;

public interface BillAuditingService {

	public List<Map<String, Object>> getBillInfo(String billNumber);

	public void updateBillStatus(String billNumber);

	public List<Map<String, Object>> getBills();

}
