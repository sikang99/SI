package net.herit.iot.onem2m.incse.manager.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.herit.iot.message.onem2m.OneM2mRequest.RESULT_CONT;
import net.herit.iot.message.onem2m.OneM2mResponse.RESPONSE_STATUS;
import net.herit.iot.onem2m.core.convertor.ConvertorFactory;
import net.herit.iot.onem2m.core.convertor.JSONConvertor;
import net.herit.iot.onem2m.core.util.OneM2MException;
import net.herit.iot.onem2m.incse.context.OneM2mContext;
import net.herit.iot.onem2m.incse.facility.OneM2mUtil;
import net.herit.iot.onem2m.incse.manager.dao.DAOInterface;
import net.herit.iot.onem2m.incse.manager.dao.ResourceDAO;
import net.herit.iot.onem2m.resource.CSEBase;
import net.herit.iot.onem2m.resource.Container;
import net.herit.iot.onem2m.resource.Delivery;
import net.herit.iot.onem2m.resource.Resource;


public class DeliveryDAO extends ResourceDAO implements DAOInterface {
	
	private Logger log = LoggerFactory.getLogger(DeliveryDAO.class);

	public DeliveryDAO(OneM2mContext context) {
		super(context);
	}

	@Override
	public String resourceToJson(Resource res) throws OneM2MException {
		try {
			
			JSONConvertor<Delivery> jc = (JSONConvertor<Delivery>)ConvertorFactory.getJSONConvertor(Delivery.class, Delivery.SCHEMA_LOCATION);
			return jc.marshal((Delivery)res);
			
		} catch (Exception e) {
			log.debug("Handled exception", e);			
			throw new OneM2MException(RESPONSE_STATUS.INTERNAL_SERVER_ERROR, "Json generation error:"+res.toString());
		}
	}

	@Override
	public void create(Resource resource) throws OneM2MException {
		
		super.create(resource);
		
	}

	@Override
	public void update(Resource resource) throws OneM2MException {
		
		super.update(resource);
				
	}

//	@Override
//	public Resource retrieveByUri(String uri, RESULT_CONT rc) throws OneM2MException {
//		
//		return this.retrieve(URI_KEY, uri, new JSONConvertor<Delivery>(Delivery.class), rc);
//		
//	}
//
//	@Override
//	public Resource retrieveByResId(String id, RESULT_CONT rc) throws OneM2MException {
//		
//		return this.retrieve("resourceID", id, new JSONConvertor<Delivery>(Delivery.class), rc);
//		
//	}
//	
//	@Override
//	public void deleteByUri(String uri) throws OneM2MException {
//				
//		deleteDocument(URI_KEY, uri);
//		
//		
//	}
//
//	@Override
//	public void deleteByResId(String resId) throws OneM2MException {	
//
//		deleteDocument(RESID_KEY, resId);
//		
//		
//	}
	
	@Override
	public void delete(String id) throws OneM2MException {
		
		String resourceID = (String)this.getAttribute(OneM2mUtil.isUri(id) ? URI_KEY : RESID_KEY,  id, RESID_KEY);	

		deleteChild(resourceID);
		
		deleteDocument(OneM2mUtil.isUri(id) ? URI_KEY : RESID_KEY, id);
		
	}

	@Override
	public Resource retrieve(String id, RESULT_CONT rc) throws OneM2MException {
		
		return retrieve(OneM2mUtil.isUri(id) ? URI_KEY : RESID_KEY, id, 
				ConvertorFactory.getJSONConvertor(Delivery.class, Delivery.SCHEMA_LOCATION), rc);
		
	}
	
	
}
