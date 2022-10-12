starting_word = input("Enter a word to be evaluated as a starting word: ").lower()
print(f"Test word: {starting_word}")

buckets = {
    '00000': 0,
    '10000': 0,
    '01000': 0,
    '00100': 0,
    '00010': 0,
    '00001': 0,
    '11000': 0,
    '10100': 0,
    '10010': 0,
    '10001': 0,
    '01100': 0,
    '01010': 0,
    '01001': 0,
    '00110': 0,
    '00101': 0,
    '00011': 0,
    '11100': 0,
    '11010': 0,
    '11001': 0,
    '10110': 0,
    '10101': 0,
    '10011': 0,
    '01110': 0,
    '01101': 0,
    '01011': 0,
    '00111': 0,
    '11110': 0,
    '11101': 0,
    '11011': 0,
    '10111': 0,
    '01111': 0,
    '11111': 0
}

possible_words = ['xxxxx','sxxxx', 'xlxxx','xxaxx','xxxtx','xxxxe','slxxx','sxaxx','sxxtx','sxxxe','xlaxx','xlxtx','xlxxe','xxatx','xxaxe','xxxte',
'slaxx','slxtx','slxxe','sxatx','sxaxe','sxxte','xlatx','xlaxe','xlxte','xxate','slatx','slaxe','slxte','sxate','xlate','slate']
#print(len(possible_words))





def evaluate_guess(guess, starting_word):
    letters = [*starting_word]
    #print(letters)
    bool_map = ''
    for letter in letters:
        if letter in guess:
            #print(letter)
            bool_map += '1'
        else:
            bool_map += '0'
    #print(bool_map)
    return bool_map


for pos_word in possible_words:
    bucket = evaluate_guess(pos_word,starting_word)
    buckets[bucket] += 1

#print(buckets)

total_guesses = len(possible_words)
#print(total_guesses)

for bucket in buckets:
    buckets[bucket] = buckets[bucket]/total_guesses * 100

print(buckets)