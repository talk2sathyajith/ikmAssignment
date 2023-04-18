package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CouponDTO;
import exception.CustomAPIException;
import service.CouponMstService;

@RestController
@RequestMapping("/api/couponmst")
public class CouponMstController {

	@Autowired
	CouponMstService couponMstService;

	@PostMapping
	public ResponseEntity<CouponDTO> addCoupon(@RequestBody CouponDTO couponDto) throws CustomAPIException {
		fieldValidation(couponDto);
		CouponDTO savedCoupon = couponMstService.addCoupon(couponDto);
		return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
	}

	private void fieldValidation(CouponDTO couponDto) {
		if (null != couponDto) {
			if (couponDto.getCouponCode().isBlank()) {
				throw new CustomAPIException(HttpStatus.BAD_REQUEST, "Coupon code Cannot be Empty");
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			// Convert Date to String
			String expirationDateString = dateFormat.format(couponDto.getExpDate());

			LocalDate expirationDate = LocalDate.parse(expirationDateString);

			if (expirationDate.isBefore(LocalDate.now())) {

				throw new CustomAPIException(HttpStatus.BAD_REQUEST, "Error: expiration date must be a future date.");

			}

			if (couponDto.getDiscountAmount() < 1 || couponDto.getDiscountAmount() > 100) {

				throw new CustomAPIException(HttpStatus.BAD_REQUEST,
						"Error: discount_amount must be within the range of 1 to 100.");

			}

		} else {
			throw new CustomAPIException(HttpStatus.BAD_REQUEST, "Invalid Data");
		}

	}
}
