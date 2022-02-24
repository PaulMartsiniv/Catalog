package catalog.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D toResponseDto(T t);
}
