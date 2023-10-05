package ex3DTO;

import java.util.List;

public class RequestListDTO {
    private List<RequestDTO> requestDTOList;

    public RequestListDTO(List<RequestDTO> requestDTOList) {
        this.requestDTOList = requestDTOList;
    }

    public List<RequestDTO> getRequestDTOList() {
        return requestDTOList;
    }
}
