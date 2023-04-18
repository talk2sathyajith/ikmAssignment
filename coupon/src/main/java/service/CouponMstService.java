package service;

import java.util.List;

import dto.CouponDTO;

public interface CouponMstService {
	CouponDTO addCoupon(CouponDTO couponDto);

	CouponDTO getCoupon(Long id);

	List<CouponDTO> getAllCoupon();

	CouponDTO updateCoupon(CouponDTO couponDto, Long id);

	void deleteCoupon(Long id);
}
