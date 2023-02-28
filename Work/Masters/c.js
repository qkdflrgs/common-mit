function bin2dec(bin) {
    var answer = 0;

    for(let i = 0; i < bin.length; i++) {
        if(bin[i] === true) {
            answer += (2 ** i);
        }
    }
    return answer;
}