package sageassistant.dao2;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.TrackingNotes;

@Mapper
public interface TrackingMapper {

	List<TrackingNotes> findTrackingNotes(@Param("TrackCode") String TrackCode);

	Integer insertTrackingNotes(@Param("TrackCode") String TrackCode, @Param("Note") String Note,
			@Param("CreateDate") String CreateDate, @Param("CreateBy") String CreateBy);

	Integer updateTrackingNotes(@Param("TrackCode") String TrackCode, @Param("Note") String Note,
			@Param("UpdateDate") String UpdateDate, @Param("UpdateBy") String UpdateBy);

	Integer deleteTrackingNotes(@Param("TrackCode") String TrackCode);
}
