package com.baturayucer.hotelsearch.service.util;

import com.baturayucer.hotelsearch.service.exception.AdvertUpdateException;
import com.baturayucer.hotelsearch.service.model.HotelAdvertiserDto;
import com.baturayucer.hotelsearch.service.model.UpdatePricesDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.function.Predicate;

/**
 * Util methods for updating adverts.
 * @author baturayucer.
 */
public final class UpdateUtils {

    private static final Logger logger = LogManager.getLogger(UpdateUtils.class);

    public static void updateHotelAdvert(
            List<HotelAdvertiserDto> hotelAdvertisers, UpdatePricesDto updatePricesDto) {

        Predicate<HotelAdvertiserDto> advertsByUpdateDto = ad ->
                ad.getHotelId().equals(updatePricesDto.getHotelId()) &&
                        ad.getAdvertiserId().equals(updatePricesDto.getAdvertiserId());

        final boolean[] updated = {false};
        hotelAdvertisers.parallelStream()
                .filter(advertsByUpdateDto).
                forEach(advertiser -> {
                    advertiser.setPrice(updatePricesDto.getPrice());
                    updated[0] = true;
                    logger.info(
                            "Advertiser updated with the Id:{}",
                            advertiser.getAdvertiserId());
                });

        if(!updated[0]) {
            throw new AdvertUpdateException("No matching advert found to update.");
        }
    }

    private UpdateUtils() {

        throw new IllegalStateException("This is a utility class");
    }
}
