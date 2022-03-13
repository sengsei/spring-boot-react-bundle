import i18n from "i18next"

export default function LanguageSelection (){
    const changeLanguage = (lng: string) => {
        i18n.changeLanguage(lng)
    }
    return (
            <div className={'space-x-1 mr-2'}>
                <button className={'bg-gray-400'} onClick={() => changeLanguage('de')}>de</button>
                <button className={'bg-gray-400'} onClick={() => changeLanguage('en')}>en</button>
            </div>

    );
}
