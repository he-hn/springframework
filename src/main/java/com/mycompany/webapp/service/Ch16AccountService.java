package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.mycompany.webapp.dao.mybatis.Ch16AccountDao;
import com.mycompany.webapp.dto.Ch16Account;
import com.mycompany.webapp.exception.Ch16NotFoundAccountException;

@Service
public class Ch16AccountService {
	private static final Logger logger = LoggerFactory.getLogger(Ch16AccountService.class);
	
	public enum TransferResult {
		SUCCESS,
		FAIL_NOT_FOUND_ACCOUNT,
		FAIL_NOT_ENOUGH_BALANCE
	}
	
	@Resource
	private TransactionTemplate transactionTemplate;
	
	@Resource
	private Ch16AccountDao accountDao;
	
	public List<Ch16Account> getAccounts() {
		logger.info("실행");
		List<Ch16Account> accounts = accountDao.selectAll();
		return accounts;
	}
	
	public TransferResult transfer1(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		String result = transactionTemplate.execute(new TransactionCallback<String>() { //transaction 콜백
			@Override
			public String doInTransaction(TransactionStatus status) {
				try {
					//출금하기
					Ch16Account fromAccount = accountDao.selectByAno(fromAno);
					if(fromAccount == null) {
						throw new Ch16NotFoundAccountException("출금 계좌 없음");
					}
					fromAccount.setBalance(fromAccount.getBalance() - amount);
					accountDao.updateBalance(fromAccount);
					
					//입금하기
					Ch16Account toAccount = accountDao.selectByAno(toAno);
					if(toAccount == null) { //null이면 입금 계좌가 없음
						throw new Ch16NotFoundAccountException("입금 계좌 없음");
					}
					toAccount.setBalance(toAccount.getBalance() + amount);
					accountDao.updateBalance(toAccount);
					return "success";
				} catch(Exception e) {
					//트랜잭션 작업을 모두 취소
					status.setRollbackOnly(); //롤백
					return "fail";
				}
			}
		});
		
		if(result.equals("success")) {
			return TransferResult.SUCCESS;
		} else {
			return TransferResult.FAIL_NOT_FOUND_ACCOUNT;
		}
	}
	
	@Transactional //트랜잭션에 관한 코드를 작성할 필요 없다. 자동 롤백된다. 메소드에 붙기 때문에 전체가 트랜잭션 처리 된다. 프로그래밍 방식은 개별적인 트랜잭션 처리가 가능하다. 모든 작업들을 모두 성공시키거나 실패 시킬때 필요하다
	public void transfer2(int fromAno, int toAno, int amount) {
		logger.info("실행");
		
		//출금하기
		Ch16Account fromAccount = accountDao.selectByAno(fromAno); //출금 계좌 존재여부 확인
		if(fromAccount == null) {
			throw new Ch16NotFoundAccountException("출금 계좌 없음");
		}
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		accountDao.updateBalance(fromAccount);
		
		//입금하기
		Ch16Account toAccount = accountDao.selectByAno(toAno);
		if(toAccount == null) {
			throw new Ch16NotFoundAccountException("입금 계좌 없음");
		}
		toAccount.setBalance(toAccount.getBalance() + amount);
		accountDao.updateBalance(toAccount);
	}
}














