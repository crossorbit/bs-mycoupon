package cnabookstore;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MyCouponRepository extends CrudRepository<MyCoupon, Long> {

    Optional<MyCoupon> findByCouponId(Long couponId);
}