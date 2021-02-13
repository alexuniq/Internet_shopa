package eu.retarded.internetstore.core.requests.order;

import eu.retarded.internetstore.core.services.validators.OrderExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetOrderListPagingRequest {

    @OrderExist
    private final int page;
}
