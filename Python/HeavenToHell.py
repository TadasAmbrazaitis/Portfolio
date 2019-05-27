import pygame
import random
import sys

pygame.init()

width = 400
height = 600

red = (255,0,0)
blue = (0,0,255)
white = (255,255,255)
black = (0,0,0)

player_size = 25
player_pos = [width/2, 0]

enemy_size = 25
enemy_pos = [random.randint(0,width-enemy_size), height-enemy_size]
enemy_list = [enemy_pos]

speed = 7
enemy_amount = 5

screen = pygame.display.set_mode((width, height))

game_over = False

score = 0

clock = pygame.time.Clock()

myFont = pygame.font.SysFont("comicsansms", 20)

def draw_background(score):
    if score <= 50:
        background_colour = (255,255,0+score*4)
    elif score <= 75:
        background_colour = (0, 505-score*5, 0)
    elif score <= 100:
        background_colour = (250-score*2, 175-score, 0)
    elif score <= 200:
        background_colour = (250-score, 250-score, 250-score)
    elif score <= 355:
        background_colour = (score-100,0,0)
    else: 
        background_colour = (255, 0, 0)
    screen.fill(background_colour)

def set_level(score, speed):
    if speed <= 12: 
        speed = score/50 + 7
    return speed

def spawn_enemies(enemy_amount, score, enemy_list):
    if enemy_amount <= 7:
        enemy_amount = score/100 + 5
    
    delay = random.randint(1,100)
    if len(enemy_list) < enemy_amount and delay < 25:
        x_pos = random.randint(0,width-enemy_size)
        y_pos = height-enemy_size
        enemy_list.append([x_pos, y_pos])

def draw_enemies(enemy_list):
    for enemy_pos in enemy_list:
        pygame.draw.rect(screen, black, (enemy_pos[0], enemy_pos[1], enemy_size, enemy_size))
        pygame.draw.rect(screen, red, (enemy_pos[0]+5, enemy_pos[1]+5, enemy_size-10, enemy_size-10))

def update_enemy_positions(enemy_list, score):
	for idx, enemy_pos in enumerate(enemy_list):
		if enemy_pos[1] <= height and enemy_pos[1] > 0:
			enemy_pos[1] -= speed
		else:
			enemy_list.pop(idx)
			score += 1
	return score

def collision_check(enemy_list, player_pos):
	for enemy_pos in enemy_list:
		if detect_collision(enemy_pos, player_pos):
			return True
	return False

def detect_collision(player_pos, enemy_pos):
	p_x = player_pos[0]
	p_y = player_pos[1]

	e_x = enemy_pos[0]
	e_y = enemy_pos[1]   

	if (e_x >= p_x and e_x < (p_x + player_size)) or (p_x >= e_x and p_x < (e_x+enemy_size)):
		if (e_y >= p_y and e_y < (p_y + player_size)) or (p_y >= e_y and p_y < (e_y+enemy_size)):
			return True
	return False

while not game_over:
    
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
            
        if event.type == pygame.KEYDOWN:
            
            x = player_pos[0]
            y = player_pos[1]
            
            if event.key == pygame.K_LEFT:
                x -= player_size
            elif event.key == pygame.K_RIGHT:
                x += player_size
                
            player_pos = [x,y]
            
    draw_background(score)
    
    spawn_enemies(enemy_amount, score, enemy_list)
    score = update_enemy_positions(enemy_list, score)
    speed = set_level(score, speed)
    text = "Score:" + str(score)
    label = myFont.render(text, 1, white, black)
    screen.blit(label, (width-240, height-40))
    
    # Border control
    if player_pos[0] >= width:
        player_pos[0] = width-player_size  
    if player_pos[0] <= 0:
        player_pos[0] = 0

    if collision_check(enemy_list, player_pos):
        game_over = True
        break

    draw_enemies(enemy_list)

    pygame.draw.rect(screen, black, (player_pos[0], player_pos[1], player_size, player_size))
    pygame.draw.rect(screen, blue, (player_pos[0]+5, player_pos[1]+5, player_size-10, player_size-10))
    clock.tick(30)

    pygame.display.update()
