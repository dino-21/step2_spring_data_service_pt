package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.net.ano.Service;

@Controller
@Log4j
@RequestMapping("/board")
//@AllArgsConstructor
@RequiredArgsConstructor
public class BoardController {

	// @Autowired
	private final BoardService boardservice;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list...............");
		List<BoardVO> list = boardservice.getList();
		
		list.forEach(board-> log.info(board));
		model.addAttribute("list", list);
	}
	
	
	 @PostMapping("/register")
     public String register(BoardVO vo, RedirectAttributes rttr) {
    	 log.info("register......." +vo);
    	 
    	boardservice.register(vo);
    	 
    	 rttr.addFlashAttribute("result", vo.getBno());
    	 return "redirect:/board/list";
     }
	
	 
	 @GetMapping("/get")
	 public void testget(@RequestParam("bno") Long bno, Model model) {
		 log.info("/get : " + bno);
		 BoardVO vo = boardservice.get(bno);
		 log.info(vo);
		 model.addAttribute("board", boardservice.get(bno));
	 }
	 
	    
     @PostMapping("/modify")
     public String modify(BoardVO board, RedirectAttributes rttr) {
    	 log.info("modify:" + board);
    	 if(boardservice.modify(board)) {
    		 rttr.addFlashAttribute("result", "success");
    	 }
    	 return "redirect:/board/list";
     }
     
     
     @PostMapping("/remove")
     public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr ) {
    	 log.info("remove...." +bno);
    	 if(boardservice.remove(bno)) {
    		 rttr.addFlashAttribute("result", "success");
    	 }
    	 return "redirect:/board/list";
     }
}
