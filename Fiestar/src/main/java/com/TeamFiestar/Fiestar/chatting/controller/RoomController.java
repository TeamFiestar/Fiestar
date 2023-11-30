//package com.TeamFiestar.Fiestar.chatting.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
//import com.TeamFiestar.Fiestar.chatting.repository.ChatRoomRepository;
//import com.TeamFiestar.Fiestar.chatting.service.ChattingService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/chat")
//@Log4j2
//public class RoomController {
//
//	private final ChattingService service;
//	@PostMapping
//	public ChatRoom createRoom(@RequestParam String name) {
//		return service.createRoom(name);
//	}
//	
//	
//	
//	
//	
////	private final ChatRoomRepository repository;
//	
////	@GetMapping("rooms")
////    public ModelAndView rooms(){
////
////        log.info("# All Chat Rooms");
////        ModelAndView mv = new ModelAndView("chat/rooms");
////
////        mv.addObject("list", repository.allRooms());
////
////        return mv;
////    }
////	
////	@PostMapping("room")
////	public String create(@RequestParam String name, RedirectAttributes ra) {
////		log.info("create chat room , name : " + name);
////		ra.addFlashAttribute("roomName", repository.createChatRoom(name));
////		return "redirect:/chat/rooms";
////	}
////	
////	@GetMapping("room")
////	public void getRoom(String roomId, Model model) {
////		log.info("get Chat Room, roomId : " + roomId);
////		model.addAttribute("room", repository.findRoomId(roomId));
////	}
//	
//}
