package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    @Transactional
    public void 구독하기(int fromUserId, int toUserId){
        // save 하려면 subscribe 객체가 필요한데, 받는 값들은 fromUserId와 toUserId는 int 값이며 int 값들로는 객체를 못만든다.
        // 이럴 때는 네이티브 쿼리를 이용해서 더 간단하게 구현을 할 수 있다.
        // 왜냐하면 save를 하려면 Object 받아와서 findById 해가지고 매핑하고 해야되는데 복잡함...
        subscribeRepository.mSubscribe(fromUserId,toUserId);
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId){
        subscribeRepository.mUnSubscribe(fromUserId,toUserId);
    }
}
