package com.baturayucer.hotelsearch.service.util;

import com.baturayucer.hotelsearch.service.model.HotelAdvertiserDto;
import com.baturayucer.hotelsearch.service.model.UpdatePricesDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class UpdateUtils {

    private static final Logger logger = LogManager.getLogger(UpdateUtils.class);

    public static void updateHotelAdvert(List<HotelAdvertiserDto> hotelAdvertisers, UpdatePricesDto updatePricesDto) {

        final boolean[] updated = {false};
        hotelAdvertisers.parallelStream()
                .filter(ad -> ad.getAdvertiserId().equals(updatePricesDto.getAdvertiserId()) &&
                        ad.getHotelId().equals(updatePricesDto.getHotelId())).
                forEach(advertiser -> {
                    advertiser.setPrice(updatePricesDto.getPrice());
                    updated[0] = true;
                    logger.info("Advertiser updated with the Id:{}", advertiser.getAdvertiserId());
                });

        if(!updated[0]) {
            throw new RuntimeException("Could Not Update");
        }
    }
}
