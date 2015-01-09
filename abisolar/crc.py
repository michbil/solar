POLYNOMIAL = 0x1021
PRESET = 0

def _initial(c):
    crc = 0
    c = c << 8
    for j in range(8):
        if (crc ^ c) & 0x8000:
            crc = (crc << 1) ^ POLYNOMIAL
        else:
            crc = crc << 1
        c = c << 1
    return crc

_tab = [ _initial(i) for i in range(256) ]

def _update_crc(crc, c):
    cc = 0xff & c

    tmp = (crc >> 8) ^ cc
    crc = (crc << 8) ^ _tab[tmp & 0xff]
    crc = crc & 0xffff
   # print format(crc,"02x")

    return crc

def crc(str):
    crc = PRESET
    for c in str:
        crc = _update_crc(crc, ord(c))


    chr1 = crc>>8
    chr2 = crc&0xFF

    if (chr1 == 40) or (chr1 == 13) or (chr1 == 10):
        chr1 = chr1 + 1

    if (chr2 == 40) or (chr2 == 13) or (chr2 == 10):
        chr2 = chr2 + 1

    chr1 = chr(chr1&0xFF)
    chr2 = chr(chr2&0xFF)

    return chr1+chr2

def crcb(*i):
    crc = PRESET
    for c in i:
        crc = _update_crc(crc, c)


    return crc
