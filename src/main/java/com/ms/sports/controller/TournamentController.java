package com.ms.sports.controller;

import lombok.RequiredArgsConstructor;
import com.ms.sports.domain.Player;
import com.ms.sports.factory.GameCalculationServiceFactory;
import com.ms.sports.factory.ParseFileServiceFactory;
import com.ms.sports.protocol.FileData;
import com.ms.sports.protocol.GameResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
public class TournamentController {
    private final ParseFileServiceFactory parseFileServiceFactory;
    private final GameCalculationServiceFactory gameCalculationServiceFactory;

    @PostMapping("/calculateTournament")
    public ResponseEntity<List<GameResult>> calculateTournament(@RequestParam("files") MultipartFile[] files) {
        try {
            List<GameResult> tournamentResult = new ArrayList<>();
            for (MultipartFile file : files) {
                FileData fileData = new FileData(file);
                List<? extends Player> playersList = parseFileServiceFactory.getParseService(fileData).parseFile(fileData);
                GameResult gameResult = gameCalculationServiceFactory.getCalculationService(fileData).calculate(playersList);
                tournamentResult.add(gameResult);
            }
            return ResponseEntity.ok(tournamentResult);
        } catch (Exception e) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
