package cnabookstore;

import cnabookstore.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyCouponViewHandler {


    @Autowired
    private MyCouponRepository myCouponRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCouponCreated_then_CREATE_1 (@Payload CouponCreated couponCreated) {
        System.out.println("##### whenCouponCreated_then_CREATE_1 start ##### ");
        try {
            if (couponCreated.isMe()) {
                // view 객체 생성
                MyCoupon myCoupon = new MyCoupon();
                // view 객체에 이벤트의 Value 를 set 함
                myCoupon.setCouponId(couponCreated.getCouponId());
                myCoupon.setCustomerId(couponCreated.getCustomerId());
                myCoupon.setOrderId(couponCreated.getOrderId());
                myCoupon.setCouponStatus(couponCreated.getCouponStatus());
                // view 레파지 토리에 save
                myCouponRepository.save(myCoupon);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCouponUsed_then_UPDATE_1(@Payload CouponUsed couponUsed) {
        System.out.println("##### whenCouponUsed_then_UPDATE_1 start ##### ");
        try {
            if (couponUsed.isMe()) {
                // view 객체 조회
                Optional<MyCoupon> myCouponOptional = myCouponRepository.findByCouponId(couponUsed.getCouponId());
                if( myCouponOptional.isPresent()) {
                    MyCoupon myCoupon = myCouponOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myCoupon.setCouponStatus(couponUsed.getCouponStatus());
                    myCoupon.setOrderId(couponUsed.getOrderId());
                    // view 레파지 토리에 save
                    myCouponRepository.save(myCoupon);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCouponRefund_then_UPDATE_2(@Payload CouponRefund couponRefund) {
        try {
            if (couponRefund.isMe()) {
                // view 객체 조회
                Optional<MyCoupon> myCouponOptional = myCouponRepository.findByCouponId(couponRefund.getCouponId());
                if( myCouponOptional.isPresent()) {
                    MyCoupon myCoupon = myCouponOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myCoupon.setCouponStatus(couponRefund.getCouponStatus());
                    // view 레파지 토리에 save
                    myCouponRepository.save(myCoupon);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}