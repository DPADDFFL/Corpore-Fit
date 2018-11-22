
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.FriendRequestRepository;
import domain.FriendRequest;

@Component
@Transactional
public class StringToFriendRequestConverter implements Converter<String, FriendRequest> {

	@Autowired
	FriendRequestRepository	friendRequestRepository;


	@Override
	public FriendRequest convert(final String text) {
		FriendRequest result;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.friendRequestRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
