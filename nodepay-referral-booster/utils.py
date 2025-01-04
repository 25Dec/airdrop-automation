import random
import string

refCodes = [
    {"name": "Chi Be", "refCode": "Fk56o4DXrfSeitn"},
    {"name": "Nhan", "refCode": "gYBMdHXb3JnSvjY"},
    {"name": "A Nhon", "refCode": "LXZ7v4v4HAG8HZM"},
]


def generate_random_string(length=15):
    characters = string.ascii_lowercase + string.digits
    return "".join(random.choices(characters, k=length))
