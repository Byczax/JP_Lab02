import random

filenameMusic = "musicList.txt"
genreList = ["Alternative", "Anime", "Blues", "Classical", "Comedy", "Country", "Dance", "Electronic", "Folk",
             "Industrial", "Instrumental", "Jazz", "Latin", "Metal", "Pop", "R&B/Soul", "Reggae", "Rock", "Vocal",
             "World"]
fileMusic = open(filenameMusic, 'w')
fileMusic.write("Nr utworu;Rodzaj utworu;Czas utworu\n")
for i in range(1, 1001):
    fileMusic.write(str(i) + ";" + str(random.choice(genreList)) + ";" + str(random.randint(60, 600)))
    fileMusic.write("\n")

fileMusic.close()

filenameAttendants = "attendantsList.txt"
fileAttendants = open(filenameAttendants, 'w')
fileAttendants.write("Nr uczestnika;Preferencje\n")
for i in range(1, 10001):
    value = random.randint(0, 100)
    fileAttendants.write(str(i) + ";" + str(random.choice(genreList)) + "," + str(value))
    if value < 100:
        fileAttendants.write("|" + str(random.choice(genreList)) + "," + str(100 - value))
    fileAttendants.write("\n")

fileAttendants.close()
