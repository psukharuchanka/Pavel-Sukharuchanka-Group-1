package com.jmp.concurrency.services.bank.service.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.jmp.concurrency.services.bank.service.dto.Account;
import com.jmp.concurrency.services.bank.service.dto.AccountMap;
import com.jmp.concurrency.services.bank.service.exception.DataManagerException;
import com.jmp.concurrency.services.bank.service.util.AppProperties;
import com.jmp.concurrency.services.bank.service.util.PropertiesConfig;

public class JAXBDataManager implements DataManager {
	
	private static final Logger logger = Logger.getLogger(JAXBDataManager.class);
	
	private static Unmarshaller jaxbUnmarshaller = null;
	
	private static Marshaller jaxbMarshaller = null;
	
	private static String dataFilePath = null;
	
	public JAXBDataManager() {
		super();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AccountMap.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        dataFilePath = PropertiesConfig.getProperty(AppProperties.DATA_FILE_PATH_PROPERTY);
		} catch (JAXBException e) {
			logger.error("Unnable to initialize JAXB context.", e);
		}
	}

	@Override
	public void write(final AccountMap accountMap) throws DataManagerException {
		try {
			File dataFile = new File(dataFilePath);
			if(!dataFile.exists()) {
				new File(FilenameUtils.getFullPath(dataFilePath)).mkdirs();
				new File(dataFilePath).createNewFile();
			}
			jaxbMarshaller.marshal(accountMap, dataFile);
		} catch (JAXBException e) {
			logger.error("Unnable to marshall AccountMap", e);
			throw new DataManagerException(e);
		} catch (IOException e) {
			logger.error("Unnable to create data file", e);
			throw new DataManagerException(e);
		}
	}

	@Override
	public AccountMap read() throws DataManagerException {
		try {
			File dataFile = new File(dataFilePath);
			if(!dataFile.exists()) {
				new File(FilenameUtils.getFullPath(dataFilePath)).mkdirs();
				new File(dataFilePath).createNewFile();
				AccountMap accountMap = new AccountMap();
				accountMap.setAccountMap(new HashMap<Integer, Account>());
				return accountMap;
			}
			return (AccountMap) jaxbUnmarshaller.unmarshal(new File(dataFilePath));
		} catch (JAXBException e) {
			logger.error("Unnable to unmarshall AccountMap", e);
			throw new DataManagerException(e);
		} catch (IOException e) {
			logger.error("Unnable to create data file", e);
			throw new DataManagerException(e);
		}
	}
}