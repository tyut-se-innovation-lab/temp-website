package tyut.selab.vote.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import tyut.selab.vote.domain.DTO.VoteOptionDTO;
import tyut.selab.vote.domain.DTO.VoteResultRequest;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.VoteResultDetails;
import tyut.selab.vote.domain.vo.VoteOptionDetailsVo;

import java.util.List;


@Mapper(componentModel = "spring")
public interface VoteInfoConverter {

   @Mappings(
           @Mapping(target = "voteOptionDetails",expression = "java(toVoteOptionDetailsVo(voteInfo.getVoteOptionVoList()))")
   )
   VoteResultDetails voteInfoToVoteResultDetails(VoteInfo voteInfo);
   @Mappings(
        @Mapping(target = "userNames",ignore = true)
     )
   List<VoteOptionDetailsVo> voteOptionDtoToDetailsVo(List<VoteOptionDTO> voteOptionDTO);
   default List<VoteOptionDetailsVo> toVoteOptionDetailsVo(List<VoteOptionDTO> voteOptionDTOs){
      List<VoteOptionDetailsVo> voteOptionDetailsVos = voteOptionDtoToDetailsVo(voteOptionDTOs);
      return voteOptionDetailsVos;
   }
}
