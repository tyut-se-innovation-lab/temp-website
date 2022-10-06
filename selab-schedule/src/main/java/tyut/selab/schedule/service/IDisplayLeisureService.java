package tyut.selab.schedule.service;

import tyut.selab.schedule.domain.vo.DisplayLeisureRequest;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;

import java.util.List;

public interface IDisplayLeisureService {
    List<DisplayLeisureResponse> getLeisure(DisplayLeisureRequest displayLeisureRequest);
}
