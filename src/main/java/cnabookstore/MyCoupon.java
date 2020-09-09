package cnabookstore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MyCoupon_table")
public class MyCoupon {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long couponId;
        private Long customerId;
        private Long orderId;
        private String couponStatus;


        public Long getCouponId() {
            return couponId;
        }

        public void setCouponId(Long couponId) {
            this.couponId = couponId;
        }
        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }
        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }
        public String getCouponStatus() {
            return couponStatus;
        }

        public void setCouponStatus(String couponStatus) {
            this.couponStatus = couponStatus;
        }

}
