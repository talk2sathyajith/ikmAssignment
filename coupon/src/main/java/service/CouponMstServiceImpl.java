package service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.CouponDTO;
import entity.CouponMst;
import exception.ResourceNotFoundException;
import repository.CouponMstRepository;

@Service
public class CouponMstServiceImpl implements CouponMstService {

	@Autowired
	private CouponMstRepository couponMstRepository;
	private ModelMapper modelMapper;

	@Override
	public CouponDTO addCoupon(CouponDTO couponDto) {
		CouponMst couponMst = modelMapper.map(couponDto, CouponMst.class);
		CouponMst savedCoupon = couponMstRepository.save(couponMst);
		return modelMapper.map(savedCoupon, CouponDTO.class);
	}

	@Override
	public CouponDTO updateCoupon(CouponDTO couponDto, Long id) {
		CouponMst coupon = couponMstRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Coupon", "id", id));

		coupon.setCouponCode(couponDto.getCouponCode());
		coupon.setExpDate(couponDto.getExpDate());
		coupon.setDiscountAmount(couponDto.getDiscountAmount());

		CouponMst updateCouponMst = couponMstRepository.save(coupon);

		return modelMapper.map(updateCouponMst, CouponDTO.class);
	}

	@Override
	public void deleteCoupon(Long id) {
		CouponMst couponMst = couponMstRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("coupons", "id", id));
		if (null != couponMst)
			couponMstRepository.deleteById(id);
	}

	@Override
	public CouponDTO getCoupon(Long id) {

		CouponMst coupon = couponMstRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Coupon", "id", id));

		return modelMapper.map(coupon, CouponDTO.class);
	}

	@Override
	public List<CouponDTO> getAllCoupon() {
		List<CouponMst> coupons = couponMstRepository.findAll();

		return coupons.stream().map((coupon) -> modelMapper.map(coupon, CouponDTO.class)).collect(Collectors.toList());
	}
}
