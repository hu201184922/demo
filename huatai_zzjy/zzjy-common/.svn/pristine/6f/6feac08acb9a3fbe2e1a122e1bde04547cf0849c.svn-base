package com.fairyland.jdp.framework.scanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScannerHandlerImpl implements ScannerHandler{
	
	@Autowired
	private URLScannerHandler urlScannerHandler;

	@Override
	public void scanner() {
		urlScannerHandler.scanner();
	}

}
