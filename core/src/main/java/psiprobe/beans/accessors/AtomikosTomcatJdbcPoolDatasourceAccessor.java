/**
 * Licensed under the GPL License. You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.
 */
package psiprobe.beans.accessors;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import psiprobe.model.DataSourceInfo;

public class AtomikosTomcatJdbcPoolDatasourceAccessor implements DatasourceAccessor {
	public DataSourceInfo getInfo(Object resource) throws Exception {
		DataSourceInfo dataSourceInfo = null;
		if(canMap(resource)) {
			AtomikosDataSourceBean source = (AtomikosDataSourceBean) resource;
			dataSourceInfo = new DataSourceInfo();
			dataSourceInfo.setBusyConnections(source.poolTotalSize() - source.poolAvailableSize());
			dataSourceInfo.setEstablishedConnections(source.poolAvailableSize());
			dataSourceInfo.setMaxConnections(source.getMaxPoolSize());
			dataSourceInfo.setJdbcUrl(source.getXaProperties().getProperty("URL"));
			dataSourceInfo.setUsername(source.getXaProperties().getProperty("user"));
			dataSourceInfo.setResettable(false);
			dataSourceInfo.setType("atomikos");
		}
		return dataSourceInfo;
	}

	public boolean reset(Object resource) throws Exception {
		return false;
	}

	public boolean canMap(Object resource) {
	    return resource != null 
	    		&& "com.atomikos.jdbc.AtomikosDataSourceBean".equals(resource.getClass().getName())
	            && resource instanceof AtomikosDataSourceBean;
	}
}