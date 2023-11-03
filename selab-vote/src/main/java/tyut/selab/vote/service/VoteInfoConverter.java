package tyut.selab.vote.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tyut.selab.vote.domain.DTO.VoteOptionDTO;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.VoteOptionDetailsVo;

import java.util.List;


@Mapper(componentModel = "spring")
public interface VoteInfoConverter {




   @Mappings(
        @Mapping(target = "userNames",ignore = true)
     )
   List<VoteOptionDetailsVo> voteOptionDtoToDetailsVo(List<? extends VoteOptionDTO> voteOptionDTO);

   default List<VoteOptionDetailsVo> toVoteOptionDetailsVo(List<VoteOptionDTO> voteOptionDTOs){
      List<VoteOptionDetailsVo> voteOptionDetailsVos = voteOptionDtoToDetailsVo(voteOptionDTOs);
      return voteOptionDetailsVos;
   }

}
