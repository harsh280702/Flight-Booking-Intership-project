package com.fbcode.Fb.Booking.Application.service.implemenation.ticket;

import com.fbcode.Fb.Booking.Application.dto.ticket.TIcketEntityDto;
import com.fbcode.Fb.Booking.Application.dto.user.UserResponseDTO;
import com.fbcode.Fb.Booking.Application.entity.ticket.TicketEntity;
import com.fbcode.Fb.Booking.Application.exception.OurException;
import com.fbcode.Fb.Booking.Application.mapper.Utils;
import com.fbcode.Fb.Booking.Application.repositry.TicketRepository;
import com.fbcode.Fb.Booking.Application.service.InterFace.ticket.ITicketServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImple  implements ITicketServices {

    private final TicketRepository  ticketRepository;

    @Override
    public Mono<UserResponseDTO> createTicket(TicketEntity ticketEntity) {
        return Mono.fromCallable(()->{
                   UserResponseDTO userResponseDTO = new UserResponseDTO();
                   try{
                       TicketEntity ticketEntity1 =ticketRepository.save(ticketEntity);
                       TIcketEntityDto tIcketEntityDto = Utils.mappedTicketEntityToTicketEntityDto(ticketEntity1);
                       userResponseDTO.settIcketEntityDto(tIcketEntityDto);
                       userResponseDTO.setStatusCode(200);
                       userResponseDTO.setMessage("Success");
                   }
                   catch (OurException ex){
                       userResponseDTO.setStatusCode(400);
                       userResponseDTO.setMessage(ex.getMessage());
                   }
                   catch(Exception e){
                       userResponseDTO.setStatusCode(500);
                       userResponseDTO.setMessage("FAILURE"+e.getMessage());
                   }

                    return userResponseDTO;

        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> updateTicket( Long id ,TicketEntity ticketEntity) {
        return Mono.fromCallable(()->{
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            try{
                TicketEntity ticketEntity1 =ticketRepository.findticketById(id).orElseThrow(()->new OurException("ticket not found"));
                if(ticketEntity1!=null){

                }
                TIcketEntityDto tIcketEntityDto = Utils.mappedTicketEntityToTicketEntityDto(ticketEntity1);
                userResponseDTO.settIcketEntityDto(tIcketEntityDto);
                userResponseDTO.setStatusCode(200);
                userResponseDTO.setMessage("Success");
            }
            catch (OurException ex){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(ex.getMessage());
            }
            catch(Exception e){
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage("FAILURE"+e.getMessage());
            }

            return userResponseDTO;

        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> deleteTicket(Long id) {
        return Mono.fromCallable(()->{
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            try{
                TicketEntity ticketEntity1 =ticketRepository.findticketById(id).orElseThrow(()->new OurException("Ticket Not Found"));
                if(ticketEntity1!=null){
                   ticketRepository.delete(ticketEntity1);
                    userResponseDTO.setStatusCode(200);
                    userResponseDTO.setMessage("Success");
                }
                else {
                    userResponseDTO.setStatusCode(400);
                    userResponseDTO.setMessage("FAILURE");
                }

            }
            catch (OurException ex){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(ex.getMessage());
            }
            catch(Exception e){
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage("FAILURE"+e.getMessage());
            }

            return userResponseDTO;

        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<UserResponseDTO> getTickets() {
        return Mono.fromCallable(()->{
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            try{
               List<TicketEntity> ticketEntity1 =ticketRepository.findAll();
               List<TIcketEntityDto> tIcketEntityDtos= Utils.mappedTicketEntityListToTicketEntityDtoList(ticketEntity1);
               userResponseDTO.setTicketEntityList(tIcketEntityDtos);
                    userResponseDTO.setStatusCode(200);
                    userResponseDTO.setMessage("Success");

            }
            catch (OurException ex){
                userResponseDTO.setStatusCode(400);
                userResponseDTO.setMessage(ex.getMessage());
            }
            catch(Exception e){
                userResponseDTO.setStatusCode(500);
                userResponseDTO.setMessage("FAILURE"+e.getMessage());
            }

            return userResponseDTO;
        }).flatMapMany(Flux::just)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<UserResponseDTO> getTicket(Long ticketId) {
        return Mono.fromCallable(()->{
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    try{
                        TicketEntity ticketEntity1 =ticketRepository.findticketById(ticketId).orElseThrow(() -> new OurException("ticket not found"));
                        TIcketEntityDto tIcketEntityDtos= Utils.mappedTicketEntityToTicketEntityDto(ticketEntity1);
                        userResponseDTO.settIcketEntityDto( tIcketEntityDtos);
                        userResponseDTO.setStatusCode(200);
                        userResponseDTO.setMessage("Success");

                    }
                    catch (OurException ex){
                        userResponseDTO.setStatusCode(400);
                        userResponseDTO.setMessage(ex.getMessage());
                    }
                    catch(Exception e){
                        userResponseDTO.setStatusCode(500);
                        userResponseDTO.setMessage("FAILURE"+e.getMessage());
                    }

                    return userResponseDTO;
                })
                .subscribeOn(Schedulers.boundedElastic());
    }
}
