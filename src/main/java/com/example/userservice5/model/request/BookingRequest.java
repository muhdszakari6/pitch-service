package com.example.userservice5.model.request;

import com.example.userservice5.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    @NotNull()
    BookingStatus status;

}
